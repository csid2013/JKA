package modele;

import java.util.ArrayList;

import modele.deplacements.Mur;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.mobiles.MotifMobile;

public class Jeu {
	
	private int scoreMax;
	private int niveau;
	private int niveauActuel;
	private boolean gagne;
	private boolean jeuTermine;
	private boolean reinitialiser;
	private Environnement env;
	private ArrayList<MotifMobile> listeMenaces;
	private ArrayList<Mur> listeMurs;
	
	public Jeu(int scoreMax, int niveau, int width, int height, ArrayList<PtSkeleton> pointsTarget) {

		this.setScoreMax(scoreMax);
		this.niveau=niveau;
		this.niveauActuel=0;
		this.gagne=false;
		this.jeuTermine=true;
		this.reinitialiser=false;
		env = new Environnement(width,height,pointsTarget);
		env.creerMurs();
		this.listeMurs = env.getListeMurs();
		this.listeMenaces=env.getListeMotifs();
		
	}

	public void genererListeMenaces() {
		
		env.creerListeMotifs(this.niveauActuel);
		
		
	}
	
	public ArrayList<MotifMobile> getListeMenaces() {
		
		return this.listeMenaces;
		
	}
	
	public ArrayList<Mur> getListeMurs() {
		
		return this.listeMurs;
		
	}
	
	public ArrayList<Motif> getEnvironnement() {
		
		ArrayList<Motif> environnement=new ArrayList<Motif>() ;
		environnement.addAll(this.listeMenaces);
		return environnement;
		
	}
	
	public int getNiveauActuel() {
		
		return this.niveauActuel;
		
	}
	
	public int getScoreMax() {
		return scoreMax;
	}

	public void setJeuTermine(boolean statusJeu) {
		
		this.jeuTermine=statusJeu;
		
	}
	
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	
	public void setReinitialiser(boolean reinitialiser) {
		
		this.reinitialiser=reinitialiser;
		
	}

	public void setGagne(boolean gagne) {
		
		this.gagne=gagne;
		if(!gagne) {
			this.reinitialiser=true;
		} else if(gagne&&this.niveauActuel<this.niveau) {
			this.niveauActuel++;
			this.scoreMax+=100;
			this.reinitialiser=true;
		} else {
			this.jeuTermine=true;
		}
		
	}
	
	public boolean getGagne() {
		
		return this.gagne;
		
	}

	public boolean getReinitialiser() {
		
		return this.reinitialiser;
		
	}
	
	public boolean getJeuTermine() {
		
		return this.jeuTermine;
		
	}
	
}
