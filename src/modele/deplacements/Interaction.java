/*
 * Classe qui s'occupe de tout ce qui est déplacements des motifs
 */
package modele.deplacements;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;

import modele.Jeu;
import modele.Personnage;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeRectangle;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.fixes.Vecteur;
import modele.motifs.mobiles.MotifMobile;
import vue.Toile;

/**
 * 
 * @author rayanox
 */
public class Interaction {

	private Point curseur;
	private MotifMobile figureEnCollision;
	private Motif figureDuCorpsEnCollision;
	// Alphabet alpha;
	private ArrayList<MotifMobile> listeMotifsAlphabet;
	private ArrayList<Motif> listeSquelette;
	private ArrayList<Mur> listeDeMurs;
	private Personnage perso;
	private Jeu jeu;
	// private Timer t = new Timer();
	private Vecteur vecteurSouris;// vecteur de déplacement de la souris

	// A modifier pour changer la vitesse des motifs(14.1421356237 est la
	// longueur pour un vecteur(x=10,y=10))

	// Constructeur
	public Interaction(Personnage perso, int width, int height,
			ArrayList<PtSkeleton> listeNative) {

		this.jeu = new Jeu(100, 3, width, height, listeNative);
		this.listeMotifsAlphabet = this.getListeMotifs();
		this.listeSquelette = perso.getListeCorps();
		this.listeDeMurs = this.getListeMurs();
		this.figureEnCollision = null;
		this.figureDuCorpsEnCollision = null;
		this.vecteurSouris = new Vecteur();
		this.perso = perso;

	}

	public ArrayList<MotifMobile> getListeMotifs() {
		return jeu.getListeMenaces();
	}

	public ArrayList<Motif> getListeSquelette() {
		return this.listeSquelette;
	}

	public ArrayList<Mur> getListeMurs() {
		return this.jeu.getListeMurs();
	}

	public ArrayList<Motif> getListeEnvironnement() {

		return jeu.getEnvironnement();

	}

	public boolean isJeuGagne() {

		return this.jeu.getGagne();

	}

	public boolean collision() {//utilise beaucoup plus de ressources que pour la souris
    	
    	if(!jeu.getJeuTermine()) {
    		if(jeu.getReinitialiser()) {
    			perso.reinitialiserVie();
        		if(!jeu.getGagne()) {
        		perso.reinitialiserScore();
        		} 
        		jeu.setReinitialiser(false);
        	}
    		
    		this.jeu.genererListeMenaces();
        for(int i =0; i<this.listeSquelette.size(); i++) {
        	//System.out.println("OHEE");
        	for(int u=0; u<this.listeMotifsAlphabet.size(); u++) {
        		//System.out.println("i = "+i+" \t u = "+u);
        		if(this.listeMotifsAlphabet.get(u).getNiveau()>0) {
        		if(!this.listeMotifsAlphabet.get(u).getEnDeplacement()&&this.listeSquelette.get(i).intersecte(this.listeMotifsAlphabet.get(u))) {
        			
        			this.figureEnCollision = this.listeMotifsAlphabet.get(u);
        			this.figureDuCorpsEnCollision = this.listeSquelette.get(i);
        			this.jouer();
        			this.figureEnCollision.setEnCollision(true);
        			return true;
        			
        		} else {
        			this.listeMotifsAlphabet.get(u).setEnCollision(false);
        		}
        		} else {
        			
        			this.listeMotifsAlphabet.remove(u);
        			
        		}
        	}
        }
    	}
        this.figureEnCollision = null;
        this.figureDuCorpsEnCollision = null;
        return false;
    }

	public boolean getJeuTermine() {
		
		return this.jeu.getJeuTermine();
		
	}
	
	public int getScoreMax() {
		
		return this.jeu.getScoreMax();
		
	}
	
	public int getScore() {
		
		return this.perso.getScore();
		
	}
	
	public int getVie() {
		
		return this.perso.getVie();
		
	}
	
	public int getNiveau() {
		
		return this.jeu.getNiveauActuel();
		
	}
	
	public void lancerJeu() {

		this.jeu.setJeuTermine(false);

	}

	public void finirJeu() {
		
		this.jeu.setJeuTermine(true);
		
	}
	
	public void jouer() {
		if (figureDuCorpsEnCollision.hasSkeleton(Skeleton.main_droite)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.main_gauche)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.coude_droit)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.coude_gauche)) {
			
			if (!this.jeu.getJeuTermine()) {
			
			 if( perso.getScore() < jeu.getScoreMax()) {
				this.perso.setScore(figureEnCollision.getDmg());
			} else {
				this.jeu.setGagne(true);
			}
			}
			this.figureEnCollision.diminuerNiveau();
		} else if (figureDuCorpsEnCollision.hasSkeleton(Skeleton.tete)) {
			if (!this.jeu.getJeuTermine() && perso.getVie() > 0) {
				this.perso.setDommages(figureEnCollision.getDmg());
			} else {
				this.jeu.setGagne(false);
			}
		}
	}

	public void setNouvellePositionRapprochement(MotifMobile motif) {

		if (!motif.isEnRapprochement()) {

			motif.setEnRapprochement(true);
			motif.getTimer()
					.schedule(new TacheDeplacement(this, motif), 0, 100);

		} else {

			if (motif.getPourcentage() >= 50) {
				motif.setPourcentage(0);
			}

			motif.refreshTarget();
			// motif.setPourcentage(motif.getPourcentage()+10);

			if (motif.getEncollision() || motif.getEnDeplacement()) {
				motif.setPourcentage(0);
				motif.getTimer().cancel();
				motif.reInitialiserTimer();
				motif.setEnRapprochement(false);
			}

			motif.setX((int) (motif.getPoint(0).x + motif.getCoefX()));
			motif.setY((int) (motif.getPoint(0).y + motif.getCoefY()));

			motif.actualiserSurface();
		}

	}

	public void setNouvellePositionCollison(MotifMobile motif, Vecteur vecteur) {

		vecteur.translationVecteur();

		if (!motif.getEnDeplacement()) {

			motif.setCoefXdepart((vecteur.getX2() - vecteur.getX1()));// valeur
																		// à
																		// changer
																		// pour
																		// augmenter
																		// la
																		// vitesse
																		// du
																		// motif
			motif.setCoefYdepart((vecteur.getY2() - vecteur.getY1()));

			motif.setCoefX(motif.getCoefXdepart());
			motif.setCoefY(motif.getCoefYdepart());

			motif.setEnDeplacement(true);
			motif.getTimer().schedule(
					new TacheDeplacement(this, motif, vecteur), 0, 100);// lance
																		// une
																		// tache
																		// de
																		// déplacement
																		// à
																		// intervalle
																		// régulier
																		// jusqu'à
																		// annulation
																		// de la
																		// tache
																		// (cancel)
		} else {

			if (motif.getEnCollisionAvecMur() == true) {
				motif.setCoefXdepart((vecteur.getX2() - vecteur.getX1()));// valeur
																			// à
																			// changer
																			// pour
																			// augmenter
																			// la
																			// vitesse
																			// du
																			// motif
				motif.setCoefYdepart((vecteur.getY2() - vecteur.getY1()));
				motif.setEnCollisionAvecMur(false);
			}

			/*
			 * if(motif.getEncollision()){
			 * 
			 * motif.setPourcentage(0);
			 * 
			 * }
			 */
			motif.setPourcentage(motif.getPourcentage() + 5);// incrémentation
																// du
																// pourcentage

			/*
			 * motif.setCoefX(
			 * (motif.getCoefXdepart()-(motif.getCoefXdepart()*motif
			 * .getPourcentage()/100) ) );// On enlève au nouveau pourcentage
			 * 10% par rapport à l'ancien motif.setCoefY(
			 * (motif.getCoefYdepart(
			 * )-(motif.getCoefYdepart()*motif.getPourcentage()/100) ) );
			 */

			motif.setCoefX((motif.getCoefXdepart() - (motif.getCoefXdepart()
					* motif.getPourcentage() / 100)));// On enlève au nouveau
														// pourcentage 10% par
														// rapport à l'ancien
			motif.setCoefY((motif.getCoefYdepart() - (motif.getCoefYdepart()
					* motif.getPourcentage() / 100)));

			// pour éviter l'effet gravité (ces 2 lignes sont devenues
			// inutiles à cause de la réduction par pourcentage mais elles
			// restent tout de même pour garantir une sécurité)
			// if(motif.getCoefX()*signeX < 0) motif.setCoefX(0);
			// if(motif.getCoefY()*signeY < 0) motif.setCoefY(0);

			// System.out.println("coefX ="+motif.getCoefX()+"\t coefY = "+motif.getCoefY());

			if (motif.getPourcentage() >= 100) {
				// System.out.println("FINI coefX ="+motif.getCoefX()+"\t coefY = "+motif.getCoefY());
				motif.setPourcentage(0);
				motif.getTimer().cancel();
				motif.reInitialiserTimer();
				motif.setEnDeplacement(false);
			}
			// System.out.println("x = "+motif.getX());
		}
		// System.out.println("Valeurs donn�e: x "+(motif.getPoint(0).x+motif.getCoefX())+" y:"+(motif.getPoint(0).y+motif.getCoefY()));
		motif.setX((int) (motif.getPoint(0).x + motif.getCoefX()));
		motif.setY((int) (motif.getPoint(0).y + motif.getCoefY()));

		// System.out.println("Instant vecteur: x "+(v.getX2()-v.getX1())+" y:"+(v.getY2()-v.getY1()));
		motif.actualiserSurface();// on recrée une nouvelle surface adaptée
									// aux nouvelles coordonnées.
		// System.out.println("Instant vecteur apr�s actu: x "+(v.getX2()-v.getX1())+" y:"+(v.getY2()-v.getY1()));

	}

	// Méthode qui permet d'obtenir un vecteur de la même longueur que le
	// vecteur parfait, mais en gardant
	// la même direction que le premier vecteur

	// Méthode executée à chaque déplacement du corps(lancée par le
	// tracker)
	public void actualiserDeplacement() {
		if (this.collision()) {
			// Vecteur v = this.figureDuCorpsEnCollision.getVecteurMotif();
			// System.out.println(" Vecteur corps: x "+(v.getX2()-v.getX1())+" y: "+(v.getY2()-v.getY1()));
			this.setNouvellePositionCollison(this.figureEnCollision,
					this.figureDuCorpsEnCollision.getVecteurMotif());
			// v = this.figureEnCollision.getVecteurMotif();
			// System.out.println("Nouveau vecteur figure: x "+(v.getX2()-v.getX1())+" y: "+(v.getY2()-v.getY1()));
		}
		if(!this.jeu.getJeuTermine()) {
		for (int i = 0; i < this.listeMotifsAlphabet.size(); i++) {

			if (!this.listeMotifsAlphabet.get(i).getEncollision()
					&& !this.listeMotifsAlphabet.get(i).getEnDeplacement()) {
				this.setNouvellePositionRapprochement(this.listeMotifsAlphabet
						.get(i));
			}

		}
		}
		// this.affichage();
	}

	public void affichage() {
		System.out.println("Collision ?   = " + this.collision());
		// System.out.println("nb"+this.listeMotifsAlphabet.size());
	}

}
