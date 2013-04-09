/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.deplacements;

//import com.sun.java.swing.plaf.motif.resources.motif;
import java.util.TimerTask;


import modele.motifs.fixes.Motif;
import modele.motifs.fixes.Vecteur;
import modele.motifs.mobiles.MotifMobile;

/**
 *
 * @author rayanox
 */
public class TacheDeplacement extends TimerTask{
    
    private Interaction deplacement;
    private MotifMobile motif;
    private Vecteur vecteur;
    
    public TacheDeplacement(Interaction d, MotifMobile m, Vecteur v) {
        this.deplacement = d;
        this.motif = m;
        this.vecteur = v;
    }
    
    public TacheDeplacement(Interaction d, MotifMobile m) {
        this.deplacement = d;
        this.motif = m;
        this.vecteur = new Vecteur();
    }
    
    @Override
    public void run() {
    	
    	//this.collision(); 
    	this.collisionAvecMur();
        this.deplacement.setNouvellePositionCollison(this.motif, this.vecteur);
        
    }
    
    public void setVecteur(Vecteur v) {
    	
    	this.vecteur=v;
    	
    }
    
    //test la collision avec un mur et redonne un nouveau vecteur dans le cas positif
    public void collisionAvecMur() {
    	for(int i =0; i<this.deplacement.getListeMurs().size(); i++) {
    		if(this.motif.intersecte(this.deplacement.getListeMurs().get(i))) {
    			this.vecteur = this.deplacement.getListeMurs().get(i).transformationVecteurApresRebond(this.vecteur);

    			this.motif.setEnCollisionAvecMur(true);
    		}
    	}
    }
    
    public boolean intersection(Motif motif) {
        //System.out.println("PASSAGE INTERSECTION");
        if(motif.intersecte(this.motif)) {
            
            //System.out.println("PASSAGE INTERSECTION MOTIF");
            return true;
        }
            
        else return false;
    }
    
    
    //Si le motif actuel rencontre un nouveau motif, le nouveau motif va alors commencer un déplacement
    // dans la meme direction que le motif actuel
    public void collision() {
        //System.out.println("PASSAGE COLLISION");
        int i =0;//début des motifs qui sont des figures(triangles, rond, etc.)
        while(i<this.deplacement.getListeMotifs().size()) {
        	
            if(this.deplacement.getListeMotifs().get(i) != this.motif) {
                if(this.intersection(this.deplacement.getListeMotifs().get(i))) {
                   // if(this.deplacement.getListeMotifs().get(i).getEnDeplacement() == false) {
                    	//System.out.println("Tache Deplacement__Set nouvelle position !!");

                        	Vecteur vect = this.motif.getVecteurMotif();
                        	//System.out.println("Ancien vecteur: x"+(vect.getX2()-vect.getX1())+" y:"+(vect.getY2()-vect.getY1()));
                        this.deplacement.getListeMotifs().get(i).setEnCollision(true);
                        this.deplacement.setNouvellePositionCollison(this.deplacement.getListeMotifs().get(i), this.motif.getVecteurMotif());
                        	vect = this.deplacement.getListeMotifs().get(i).getVecteurMotif();
                        	//System.out.println("Nouveau vecteur: x"+(vect.getX2()-vect.getX1())+" y:"+(vect.getY2()-vect.getY1()));
                        
                    //}
                    this.deplacement.getListeMotifs().get(i).setEnCollision(true);
                } else {
                	this.deplacement.getListeMotifs().get(i).setEnCollision(false);
                }
            }
            i++;
            
        }
        
    }
}
