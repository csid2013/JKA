package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Timer;

import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Vecteur;

public abstract class MotifMobile extends Motif {

	protected boolean enDeplacement;
	protected int coefX; //coefficient de déplacement sur l'abscisse 
	protected int coefY; //coefficient de déplacement sur l'ordonnée
	protected Timer timer;
	protected int coefXdepart;
	protected int coefYdepart;
	protected int pourcentage;
	protected boolean enCollisionAvecMur;
	protected boolean enCollisionAvecFigures;
	protected boolean enRapprochement;
	protected int niveau;
	protected int nbResistance;
	
	public MotifMobile(ArrayList<PtSkeleton> listePoints, Color couleur,int niveau) {
		
		super(listePoints, couleur);
		this.listePoints=listePoints;
		this.couleur=couleur;

		this.niveau=niveau;
        this.coefX = 0;
        this.coefY = 0;
        this.coefXdepart = 0;
        this.coefYdepart = 0;
        this.enDeplacement = false;
        this.timer = new Timer();
        this.pourcentage = 0;
        this.vecteurMotif = new Vecteur((int) this.getPoint(0).x,(int) this.getPoint(0).y,(int)this.getPoint(0).x,(int)this.getPoint(0).y);
		this.enCollisionAvecFigures=false;
		this.enRapprochement=false;
	}

	
	@Override
	public abstract void actualiserSurface();

	public void refreshTarget() {
		
		Vecteur vecteurTarget = new Vecteur((int)this.getPoint(0).x,(int)this.getPoint(0).y,(int)this.getPoint(1).x,(int)this.getPoint(1).y);
		vecteurTarget.translationVecteur();
		
		this.setCoefXdepart(vecteurTarget.getX2()-vecteurTarget.getX1());
		this.setCoefYdepart(vecteurTarget.getY2()-vecteurTarget.getY1());
        
        this.setCoefX(this.getCoefXdepart());
        this.setCoefY(this.getCoefYdepart());
		
	}
	
	public int getNiveau() {
		
		return this.niveau;
		
	}
	
	public abstract int getDmg();
	
	public void setEnRapprochement(boolean b) {
		
		this.enRapprochement=b;
		
	}
	
	public boolean isEnRapprochement() {
		
		return this.enRapprochement;
		
	}
	
	public void setEnCollision(boolean b) {
		this.enCollisionAvecFigures=b;
	}
	public void diminuerNiveau() {
		
		niveau--;
		
	}
	
	public boolean getEncollision() {
		
		return this.enCollisionAvecFigures;
		
	}
	
	 public void setPourcentage(int p) {
	        this.pourcentage = p;
	    }
	    
	    public int getPourcentage() {
	        return this.pourcentage;
	    }
	    
	    public void setCoefYdepart(int y) {
	        this.coefYdepart = y;
	    }
	    
	    public int getCoefYdepart() {
	        return this.coefYdepart;
	    }
	    
	    public void setCoefXdepart(int x) {
	        this.coefXdepart = x;
	    }
	    
	    public int getCoefXdepart() {
	        return this.coefXdepart;
	    }
	    
	    
	    public void reInitialiserTimer() {
	        this.timer = new Timer();
	    }
	    
	    public Timer getTimer() {
	        return this.timer;
	    }
	    
	    public boolean getEnDeplacement() {
	        return this.enDeplacement;
	    }
	    
	    public void setEnDeplacement(boolean b) {
	        this.enDeplacement = b;
	    }
	    
	    public int getCoefX() {
	        return this.coefX;
	    }
	    
	    public int getCoefY() {
	        return this.coefY;
	    }
	    
	    public void setCoefX(int i) {
	        this.coefX = i;
	        this.actualiserVect();
	    }
	    
	    public void setCoefY(int i) {
	        this.coefY = i;
	        this.actualiserVect();
	    }   
	    
	    public boolean getEnCollisionAvecMur() {
	    	return this.enCollisionAvecMur;
	    }
	    
	    public void setEnCollisionAvecMur(boolean b) {
	    	this.enCollisionAvecMur = b;
	    }
	
}
