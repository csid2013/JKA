package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;





public class MotifFixePolygone extends Motif {

	public MotifFixePolygone(ArrayList<PtSkeleton> listePoints, Color couleur) {
		super(listePoints, couleur);
		this.actualiserSurface();
	}


	public void actualiserSurface() {
		
		this.actualiserVect();
		 Polygon p = new Polygon();
	        Pt tmp;
	        for(int i=0; i<4 && i<this.listePoints.size();i++) {
     		tmp=listePoints.get(i);
     		p.addPoint((int) tmp.x,(int) tmp.y);
     		
     	}
     	forme = p;
	    setArea(p);
	    
	}


}
