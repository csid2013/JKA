package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;




public class MotifMobileTriangle extends MotifMobile {


	private int epaisseur;
	
	public MotifMobileTriangle(ArrayList<PtSkeleton> listePoints,
			Color couleur, int epaisseur, int niveau) {
		super(listePoints, couleur, niveau);
		this.epaisseur=epaisseur;
		this.listePoints.add(new PtSkeleton());
		this.listePoints.add(new PtSkeleton());
		this.actualiserSurface();
		// TODO Auto-generated constructor stub
	}

	public Pt getEquilateral() {
       
	double x1,y1,x2,y2;
	
	x1=this.listePoints.get(0).x;
	y1=this.listePoints.get(0).y;
	x2=this.listePoints.get(2).x;
	y2=this.listePoints.get(2).y;
	
  	  Pt p3=new Pt();      
  	  double s60 = Math.sin(Math.PI / 3);    
  	  double c60 = Math.cos(Math.PI / 3);
  	    double X= c60 * (x1 - x2) - s60 * (y1 - y2) + x2;
  	    double Y= s60 * (x1 - x2) + c60 * (y1 - y2) + y2;

	      p3.x=(int) X;
  	    if(y1>=this.getPoint(1).y) {
  	      p3.y=(int) Y;		  
  	    } else {
  	  	      p3.y=(int) Y+2*(y1-Y);	
  	    }
  	      
  	    return p3;

  	    }

	@Override
	public void actualiserSurface() {
		this.actualiserVect();
		 Polygon p = new Polygon();
	        Pt pt1 = getPoint(0);
	        Pt pt2 = getPoint(2);
	        Pt pt3 = getPoint(3);
	        pt2.x=(int) pt1.x+getPoint(1).getEpaisseurSelonProfondeur(epaisseur);
	        pt2.y=(int) pt1.y;
	        pt3.x=getEquilateral().x;
	        pt3.y=getEquilateral().y;
    		p.addPoint((int) pt1.x,(int) pt1.y);
    		p.addPoint((int) pt2.x,(int) pt2.y);
    		p.addPoint((int) pt3.x,(int) pt3.y);
    	forme = p;
	    setArea(p);
		
	}

	@Override
	public int getDmg() {
		return niveau*15;
	}

}
