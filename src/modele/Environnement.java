package modele;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import modele.deplacements.Mur;
import modele.deplacements.TypeMur;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeCercle;
import modele.motifs.fixes.MotifFixeRectangle;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.mobiles.MotifMobile;
import modele.motifs.mobiles.MotifMobileCercle;
import modele.motifs.mobiles.MotifMobileDCercle;
import modele.motifs.mobiles.MotifMobileRectangle;
import modele.motifs.mobiles.MotifMobileTriangle;

public class Environnement {

	
	private ArrayList<MotifMobile> listeMotifs;
	private ArrayList<Mur> listeMurs;
	private int width,height;
	private ArrayList<PtSkeleton> listePtSkel;
	
	public Environnement(int width, int height,ArrayList<PtSkeleton> listePtSkel) {
		this.listeMotifs = new ArrayList<MotifMobile>();
		this.listeMurs = new ArrayList<Mur>();
		this.listePtSkel = listePtSkel;
		this.width=width;
		this.height=height;
	}
	
	
	
    public PtSkeleton getUpdatedPt (Skeleton s) {
    	
    	for(int i=0;i<listePtSkel.size();i++) {
    		
    		if(listePtSkel.get(i).getPartieDuCorps().equals(s)) {
    			return listePtSkel.get(i);
    		}
    		
    	}
    	
    	return new PtSkeleton(Skeleton.aucun);
    }
	
	public void creerListeMotifs(int niveau) {
	
		int tolerance=niveau+3;
		
		
		if(this.listeMotifs.size()<tolerance) {
		
		ArrayList<PtSkeleton> listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(150,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
		MotifMobile s = new MotifMobileRectangle(listePtFigures, Color.red, 50, 25,1);
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(200,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
		s = new MotifMobileRectangle(listePtFigures, Color.red, 50, 25,1);
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(width-200,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
		s = new MotifMobileRectangle(listePtFigures, Color.red, 50, 25,1);
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(width-200,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
		s = new MotifMobileRectangle(listePtFigures, Color.red, 50, 25,1);
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(50,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
		s = new MotifMobileCercle(listePtFigures, Color.red, 40,2);
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(50,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		s = new MotifMobileTriangle(listePtFigures, Color.red, 40,2);
		
		this.listeMotifs.add(s);
		listePtFigures = new ArrayList<PtSkeleton>(); 
		listePtFigures.add(new PtSkeleton(50,height-300));
		listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
		s = new MotifMobileDCercle(listePtFigures, Color.red, 40,1);
		this.listeMotifs.add(s);
		}
		  
	}
	
	
	public void creerMurs() {
		
		listeMurs = new ArrayList<>();
	       int epaisseurMur = 10;
	       PtSkeleton ptMurGauche = new PtSkeleton(0,0);
	       PtSkeleton ptMurDroit = new PtSkeleton(this.width-epaisseurMur,0);
	       PtSkeleton ptPlafond = new PtSkeleton(0,0);
	       PtSkeleton ptSol = new PtSkeleton(0,this.height-epaisseurMur);
	       ArrayList<PtSkeleton> listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptMurGauche);
	       Mur murGauche = new Mur(listePt,Color.black,epaisseurMur,this.height,TypeMur.murGauche);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptMurDroit);
	       Mur murDroit = new Mur(listePt,Color.black,this.width-epaisseurMur,this.height,TypeMur.murDroit);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptPlafond);
	       Mur plafond = new Mur(listePt,Color.black,this.width,epaisseurMur,TypeMur.plafond);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptSol);
	       Mur sol = new Mur(listePt,Color.black,this.width,epaisseurMur,TypeMur.murDroit);
	      
	       this.listeMurs.add(murGauche);
	       this.listeMurs.add(murDroit);
	       this.listeMurs.add(plafond);
	       this.listeMurs.add(sol);
		
	}
	
	public ArrayList<MotifMobile> getListeMotifs() {
		return this.listeMotifs;
	}
	
	public ArrayList<Mur> getListeMurs() {
		return this.listeMurs;
	}
	
	
}
