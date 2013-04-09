package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import modele.motifs.fixes.PtSkeleton;




public class MotifMobileCercle extends MotifMobile {


	protected int largeur;
	
	public MotifMobileCercle(ArrayList<PtSkeleton> listePoints, Color couleur,int epaisseur, int niveau) {
		super(listePoints, couleur, niveau);
		this.largeur=epaisseur;
		this.actualiserSurface();
	}
	
	public void actualiserSurface() {
			
		PtSkeleton tmp = this.getPoint(0);
    	double epaisseur=tmp.getEpaisseurSelonProfondeur(largeur);
        	
            forme = new Ellipse2D.Double(Math.abs(tmp.x-(epaisseur/2)),Math.abs(tmp.y-(epaisseur/2)), epaisseur, epaisseur);

		this.setArea(forme);
		this.actualiserVect();
	}

	@Override
	public int getDmg() {
		
		return niveau*10;
	}

}