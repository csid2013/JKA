package modele.motifs.fixes;

import outils.Geometrie;

public class Pt {
	
	public double x,y,z;
	private Geometrie geo;
	
	public Pt(double x, double y,double z) {
		
		this.x=x;
		this.y=y;
		this.z=z;
		geo = new Geometrie();
	}
	
	public Pt() {
		
		this.x=0;
		this.y=0;
		this.z=0;
		geo = new Geometrie();
		
	}
	
	public Pt(double x, double y) {
		
		this.x=x;
		this.y=y;
		this.z=0;
		geo = new Geometrie();
		
	}
	
	public void setPoint(Pt point) {
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}
	
	public void setPoint(Pt point, double angleVecteur, Pt pCentre) {
		
		setPoint(point);
		rotate(angleVecteur,pCentre);
		
	}
	
	public void rotate(double angleVecteur, Pt pCentre) {
		
		this.setPoint(geo.getRotatedPoint(new Pt(x,y,z),angleVecteur,pCentre));
		
	}
	
	//retourne l'épaisseur selon la profondeur, les valeurs de z sont comprises entre 500 et 2200
	public double getEpaisseurSelonProfondeur(int epaisseur) {
		
		double tmpZ ;
		if(z>500) {
			tmpZ = z-500;
		} else {
			return epaisseur;
		}
		
		return  (epaisseur*((1700/tmpZ)+1))/2; 
		
	}

}
