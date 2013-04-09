/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiChannel;
import javax.swing.JPanel;

import modele.Decor;
import modele.Environnement;
import modele.ExtraChecker;
import modele.Jeu;
import modele.Personnage;
import modele.deplacements.Interaction;
import modele.deplacements.Mur;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.mobiles.MotifMobile;
import outils.Alphabet;
import outils.Sound;
import outils.SoundEffect;
/**
 * 
 * @author ytiab PossÃ¨de au moins 2 plans: fond (couleur) et dimension de la
 *         toile
 */
public class Toile extends JPanel {

	private ArrayList<Motif> squeletteHerbin;
	private int width;
	private int height;
	private int tonalite;
	private MidiChannel[] mc;
	private Decor decor;
	private boolean hovered, mute;
	private Alphabet alphabetTmp;
	private boolean detected;
	private ArrayList<Motif> listeFiguresDecor;
	private ArrayList<PtSkeleton> listePtSkel;
	private Interaction interaction;
	private Environnement env;
	private ExtraChecker extraChecker;
	private Sound sound;
	
	
	public Toile(int width, int height, ArrayList<PtSkeleton> listePtSkel) {

		this.width = width;
		this.height = height;

		this.extraChecker = new ExtraChecker(listePtSkel);
		decor = new Decor(width, height);
		listeFiguresDecor = decor.getListeFiguresDecor();
		this.listePtSkel=listePtSkel;
		squeletteHerbin = new ArrayList<Motif>();

		this.setPreferredSize(new Dimension(width, height));
		this.tonalite = 0;
		detected = false;

		alphabetTmp = new Alphabet();
		sound = new Sound(); 
		sound.playStart();
	}

	public ArrayList<Motif> getListeSqueletteHerbin() {
		return this.squeletteHerbin;
	}

	public void setListeMotifs(Personnage perso) {

		this.squeletteHerbin = perso.getListeCorps();
		this.interaction = new Interaction(perso,this.width,this.height,this.listePtSkel);
	}

	public void detection() {

		this.detected = true;

	}

	int test = 0;
	
	int iterationApparitionDecor = 0;
	int iterationDebutDuJeu = 0;
	int iterationExit = 0;
	Image fauteuil = null, fenetre = null, plafonnier = null, tv = null, matrix=null,
			tapis = null;
	boolean afficherDecor = false;
	boolean debutDuJeu = false;
	
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		Motif motif;
		if(sound.aucunSon()) {
			sound.playStart();
		}
		this.interaction.actualiserDeplacement();
		iterationApparitionDecor = extraChecker
				.checkProfondeurMainDroiteEpauleDroite(iterationApparitionDecor);
		iterationDebutDuJeu = extraChecker
				.checkProfondeurDeuxMainsEpaules(iterationDebutDuJeu);
		iterationExit = extraChecker.checkDeuxMainsEquiv(iterationExit);

		if (iterationExit >= 40) {

			System.exit(0);

		}

		if (iterationApparitionDecor >= 30) {
			afficherDecor = !afficherDecor;
			iterationApparitionDecor = 0;
		}

		if (iterationDebutDuJeu >= 30) {
			debutDuJeu = !debutDuJeu;
			iterationDebutDuJeu = 0;
		}

		if (afficherDecor) {
			for (int i = 0; i < listeFiguresDecor.size() - 1; i++) {
				motif = listeFiguresDecor.get(i);
				g2d.setColor(motif.getCouleur());
				g2d.fill(motif);
			}

			if (test == 0) {
				try {
					matrix = ImageIO.read(new File("matrix2.jpg"));
					fauteuil = ImageIO.read(new File("fauteuil.gif"));
					fenetre = ImageIO.read(new File("fenetre.gif"));
					plafonnier = ImageIO.read(new File("plafonnier.gif"));
					tv = ImageIO.read(new File("tv.gif"));
					tapis = ImageIO.read(new File("tapis.gif"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				test++;
			}
			if(!debutDuJeu) {
			g2d.drawImage(fauteuil, (int) (0.5 * getWidth() - 200 * 0.5),
					(int) (0.75 * height - 90), 200, 100, this);
			g2d.drawImage(fenetre, (int) (0.5 * getWidth() - 150 * 0.5),
					(int) (0.75 * height - 300), 150, 180, this);
			g2d.drawImage(plafonnier, (int) (0.75 * getWidth() - 100 * 0.5),
					(int) (0.25 * height - 100), 100, 100, this);
			g2d.drawImage(tv, -10, (int) (0.5 * height - 100), 140, 150, this);
			g2d.drawImage(tapis, (int) (getWidth() * 0.5 - 115),
					(int) (0.75 * height), 230, 160, this);
			} 
			motif = listeFiguresDecor.get(listeFiguresDecor.size() - 1);

			motif.setColor(
					(motif.getCouleur().getRed() + (int) (Math.random() * 5)),
					(motif.getCouleur().getGreen() + (int) (Math.random() * 5)),
					(motif.getCouleur().getBlue() + (int) (Math.random() * 5)));

			g2d.setColor(motif.getCouleur());
			g2d.fill(motif);
		}
		
		if (debutDuJeu) {
			g2d.drawImage(matrix, 0,
					0, width, height, this);
			if(!sound.isRunning(2)) {
			sound.playGame();
			}
			this.interaction.lancerJeu();
			System.out.println("Score: "+this.interaction.getScore()+" Vie: "+this.interaction.getVie()+" Niveau:"+this.interaction.getNiveau());
			ArrayList<Motif> listeEnvironnement = this.interaction.getListeEnvironnement();
			for (int i = 0; i < listeEnvironnement.size(); i++) {
				motif = listeEnvironnement.get(i);
				g2d.setColor(motif.getCouleur());
				g2d.fill(motif);
			}
		} else {
			this.interaction.finirJeu();
		}
		
		if (detected) {
			if(!debutDuJeu&&!sound.isRunning(1)) {
				sound.playHome();
			}
			for (int i = 0; i < squeletteHerbin.size(); i++) {
				motif = squeletteHerbin.get(i).getMotif();
				g2d.setColor(motif.getCouleur());
				g2d.fill(motif);
			}
		}
	}


}