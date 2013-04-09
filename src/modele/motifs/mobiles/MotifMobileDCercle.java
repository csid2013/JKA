package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.ArrayList;


import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;


public class MotifMobileDCercle extends MotifMobile {

	protected int largeur;
	
	public MotifMobileDCercle(ArrayList<PtSkeleton> listePoints, Color couleur,
			int largeur,int niveau) {
		super(listePoints, couleur, niveau);
		// TODO Auto-generated constructor stub
		this.largeur=largeur;
		this.actualiserSurface();
		
	}
	
	@Override
	public void actualiserSurface() {

		PtSkeleton premierPt = listePoints.get(0);
    	PtSkeleton partieDuCorps = getPoint(1);
    		double epaisseur = (partieDuCorps.getEpaisseurSelonProfondeur(largeur));
    		int orientation = premierPt.y>partieDuCorps.y ? 1:-1;
    		
        forme = new Arc2D.Double(premierPt.x,premierPt.y,epaisseur,epaisseur, 0,orientation*180,Arc2D.CHORD);
    	
    	this.setArea(forme);
	}

	@Override
	public int getDmg() {

		return niveau*20;
	}
	

}