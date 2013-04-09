package modele.motifs.fixes;



public class PtSkeleton extends Pt {
	
	private Skeleton partieDuCorps;
	
	public PtSkeleton() {
		
		super();
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	public PtSkeleton(double x, double y) {
		
		super(x,y);
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	public PtSkeleton(Skeleton partieDuCorps) {
		super();
		this.setPartieDuCorps(partieDuCorps);
		
	}
		
	public PtSkeleton(Pt pt) {
		
		super(pt.x,pt.y,pt.z);
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	public PtSkeleton(Pt pt, Skeleton partieDuCorps) {
		
		super(pt.x,pt.y,pt.z);
		this.setPartieDuCorps(partieDuCorps);
		
	}

	public Skeleton getPartieDuCorps() {
		return partieDuCorps;
	}

	public void setPartieDuCorps(Skeleton partieDuCorps) {
		this.partieDuCorps = partieDuCorps;
	}
	
	

}
