package modele.motifs.fixes;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;





public class MotifFixeRectangle extends Motif {

	protected int largeur;
	protected int hauteur;
	
	public MotifFixeRectangle(ArrayList<PtSkeleton> listePoints,
		Color couleur,int largeur,int hauteur) {
		
		super(listePoints, couleur);
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
		forme = new Rectangle2D.Double(this.getPoint(0).x,this.getPoint(0).y,largeur,hauteur);
		this.setArea(forme);
		
	}

}
