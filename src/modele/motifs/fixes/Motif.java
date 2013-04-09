package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;




public abstract class Motif extends Area {

	protected ArrayList<PtSkeleton> listePoints;
	protected Shape forme;
	protected Color couleur;
	protected Vecteur vecteurMotif;
	
	public Motif(ArrayList<PtSkeleton> listePoints, Color couleur) {
		
		this.listePoints=listePoints;
		this.couleur=couleur;

        this.vecteurMotif = new Vecteur((int) this.getPoint(0).x,(int) this.getPoint(0).y,(int)this.getPoint(0).x,(int)this.getPoint(0).y);
	
	}
	
	public abstract void actualiserSurface();
	

	
	public Motif getMotif() {
		
		this.actualiserSurface();
		return this;
		
	}
	
	public void setArea(Shape forme) {
		
		Area surface = new Area(forme);
        this.reset();
        this.add(surface);
		
	}
	
	public PtSkeleton getPoint(int i) {
		
		return this.listePoints.get(i);
		
	}
	
	public int listePtSize() {
		
		return this.listePoints.size();
		
	}
	
	public Color getCouleur() {
		
		return this.couleur;
		
	}


	
	 public void setColor(int r, int g, int b) {
	    	
	    	
	    	this.couleur = new Color(estCouleur(r),estCouleur(g),estCouleur(b));
	    	
	    }
	    
	    public int estCouleur(int x) {
	    	
	    	if(x>255) {
	    		return 0;
	    	} else {
	    		return x;
	    	}
	    	
	    }
	    
		public void actualiserVect() {
			
			 	this.vecteurMotif.setX1(this.vecteurMotif.getX2());
		        this.vecteurMotif.setY1(this.vecteurMotif.getY2());
		        //Puis on met les nouvelles coordonnées de la souris dans le deuxieme point du vecteur soit (x2, y2)
		        this.vecteurMotif.setX2((int) this.listePoints.get(0).x);
		        this.vecteurMotif.setY2((int) this.listePoints.get(0).y);
			
		}
		
		public void setX(int x1) {
	        
	        //AJOUTÉS A LINTEGRATION
	        //On met les anciennes coordonnées dans le premier point du vecteur soit (x1,y1)
	        this.vecteurMotif.setX1(this.vecteurMotif.getX2());
	        //Puis on met les nouvelles coordonnées de la souris dans le deuxieme point du vecteur soit (x2, y2)
	        this.vecteurMotif.setX2(x1);
	        
	        this.listePoints.get(0).x = x1;
	    }

	    public void setY(int y1) {
	        
	        //AJOUTÉS À L'INTEGRATION
	        //On met les anciennes coordonnées dans le premier point du vecteur soit (x1,y1)
	        this.vecteurMotif.setY1(this.vecteurMotif.getY2());
	        //Puis on met les nouvelles coordonnées de la souris dans le deuxieme point du vecteur soit (x2, y2)
	        this.vecteurMotif.setY2(y1);
	        
	        this.listePoints.get(0).y = y1;
	        
	    }
		
		
	    public boolean intersecte(Area a) {

	        Area tmp = new Area(a);
	        tmp.intersect(this);
	        return !tmp.isEmpty();
	    }
	    
	    public boolean intesecteAvecPoint(Point p) {
	        if(this.contains(p)) return true;
	        else return false;
	    }
	
	    public Vecteur getVecteurMotif() {
	        return this.vecteurMotif;
	    }
	    
	   public boolean hasSkeleton(Skeleton skel) {
		   
		   boolean found = false;
		   
		   for(int i=0;i<this.listePtSize();i++) {
			   
			   if(this.listePoints.get(i).getPartieDuCorps().equals(skel)) {
				   found=true;
			   }
			   
		   }
		   
		   return found;
		   
	   }
	    
}
