package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import modele.motifs.fixes.PtSkeleton;

public class MotifMobileRectangle extends MotifMobile  {

	protected int largeur;
	protected int hauteur;
	
	public MotifMobileRectangle(ArrayList<PtSkeleton> listePoints,
		Color couleur,int largeur,int hauteur, int niveau) {
		super(listePoints, couleur, niveau);
		this.hauteur=hauteur;
		this.largeur=largeur;
		this.actualiserSurface();
		
	}

	public int getLargeur() {
		
		return this.largeur;
		
	}
	
	public int getHauteur() {
		
		return this.hauteur;
		
	}

	public void actualiserSurface() {
		
		forme = new Rectangle2D.Double(this.getPoint(0).x,this.getPoint(0).y, this.getPoint(2).getEpaisseurSelonProfondeur(largeur),this.getPoint(2).getEpaisseurSelonProfondeur(hauteur));
		this.setArea(forme);
		
	}

	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return niveau*5;
	}

}