package modele;
import java.util.ArrayList;

import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;


public class ExtraChecker {

	private ArrayList<PtSkeleton> listeNative;
	
	public ExtraChecker(ArrayList<PtSkeleton> listeNative) {
		
		this.listeNative=listeNative;
	}
	
    public PtSkeleton getNativePt (Skeleton s) {
    	
    	for(int i=0;i<listeNative.size();i++) {
    		
    		if(listeNative.get(i).getPartieDuCorps().equals(s)) {
    			return listeNative.get(i);
    		}
    	}
    	
    	return new PtSkeleton(Skeleton.aucun);
    		
    	}
    	
	
	public int checkProfondeurMainDroiteEpauleDroite(int nb) {
		
		double profondeurEpauleMain = this.getNativePt(Skeleton.hanche_droite).z-this.getNativePt(Skeleton.main_droite).z;
		double profondeurHancheGaucheMainGauche = this.getNativePt(Skeleton.hanche_gauche).z-this.getNativePt(Skeleton.main_gauche).z;
		
		//System.out.println(profondeurEpauleMain);
		
		if(profondeurEpauleMain>450&&profondeurHancheGaucheMainGauche<=450) {
			
			return nb+1;
			
		}
		
		return 0;
		
	}
	
public int checkProfondeurDeuxMainsEpaules(int nb) {
		
		double profondeurHancheGaucheMainGauche = this.getNativePt(Skeleton.hanche_gauche).z-this.getNativePt(Skeleton.main_gauche).z;
		double profondeurEpauleMain = this.getNativePt(Skeleton.hanche_droite).z-this.getNativePt(Skeleton.main_droite).z;
		//System.out.println(profondeurEpauleMain);
		
		if(profondeurHancheGaucheMainGauche>450&&profondeurEpauleMain>450) {
			
			return nb+1;
			
		}
		
		return 0;
		
	}

public int checkDeuxMainsEquiv(int nb) {
	
	Pt main_gauche = this.getNativePt(Skeleton.main_gauche);
	Pt main_droite = this.getNativePt(Skeleton.main_droite);
	
	double differenceMains = Math.abs(main_droite.x-main_gauche.x)+Math.abs(main_droite.y-main_gauche.y)+Math.abs(main_droite.y-main_gauche.y);
	
	if(differenceMains<=70) {
		return nb+1;
	}
	
	return 0;
}
	
	

}
