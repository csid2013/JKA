package modele;

import java.util.ArrayList;

import modele.motifs.fixes.Motif;

public class Personnage {
	
	private int vie;
	private int score;
	private ArrayList<Motif> corps;
	
	public Personnage(ArrayList<Motif> corps) {
		
		this.vie=100;
		this.score=0;
		this.corps=corps;
		
	}

	public void reinitialiserVie() {
		
		this.vie=100;
		
	}
	
	public void reinitialiserScore() {
		
		this.score=0;
		
	}
	
	public ArrayList<Motif> getListeCorps() {
		
		return this.corps;
		
	}
	
	public void setDommages(int dmg) {
		
		if((vie-dmg)>0) {
		this.vie-=dmg;
		} else {
			vie=0;
		}
		
	}
	
	public void setScore(int score) {
		this.score+=score;
		
	}
	
	public int getScore() {
		
		return this.score;
		
	}
	
	public int getVie() {
		
		return this.vie;
		
	}
	
	
	
}
