package tracker;

import java.awt.Point;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;


import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;

import processing.core.PApplet;
import processing.core.PVector;
import vue.Lancement;
import SimpleOpenNI.*;

 public class SkeletonTracker extends PApplet {
	 
	SimpleOpenNI  context;
	Lancement l;
	PtSkeleton tete,cou,epaule_gauche,epaule_droite,coude_gauche,coude_droit,main_gauche,main_droite,hanche_gauche,hanche_droite,genou_gauche,genou_droit,pied_gauche,pied_droit;
	  
	public void setup()
	{
	  // instantiate a new context

		ArrayList<PtSkeleton> listeSkel = new ArrayList<PtSkeleton>();
	 
	  tete = new PtSkeleton(Skeleton.tete);
	  listeSkel.add(tete);
	  System.out.println("\n"+tete);
	  cou  = new PtSkeleton(Skeleton.cou);
	  listeSkel.add(cou);
	  epaule_gauche = new PtSkeleton(Skeleton.epaule_gauche);
	  listeSkel.add(epaule_gauche);
	  epaule_droite = new PtSkeleton(Skeleton.epaule_droite);
	  listeSkel.add(epaule_droite);
	  coude_gauche= new PtSkeleton(Skeleton.coude_gauche);
	  listeSkel.add(coude_gauche);
	  coude_droit= new PtSkeleton(Skeleton.coude_droit);
	  listeSkel.add(coude_droit);
	  main_droite= new PtSkeleton(Skeleton.main_droite);
	  listeSkel.add(main_droite);
	  main_gauche= new PtSkeleton(Skeleton.main_gauche);
	  listeSkel.add(main_gauche);
	  hanche_gauche= new PtSkeleton(Skeleton.hanche_gauche);
	  listeSkel.add(hanche_gauche);
	  hanche_droite= new PtSkeleton(Skeleton.hanche_droite);
	  listeSkel.add(hanche_droite);
	  genou_gauche= new PtSkeleton(Skeleton.genou_gauche);
	  listeSkel.add(genou_gauche);
	  genou_droit= new PtSkeleton(Skeleton.genou_droit);
	  listeSkel.add(genou_droit);
	  pied_gauche= new PtSkeleton(Skeleton.pied_gauche);
	  listeSkel.add(pied_gauche);
	  pied_droit= new PtSkeleton(Skeleton.pied_droit);
	  listeSkel.add(pied_droit);
	  
	  
	  context = new SimpleOpenNI(this);
	 
	  // enable depthMap generation 
	  context.enableDepth();
	  try {

			l = new Lancement(context.depthWidth(),context.depthHeight(),listeSkel);
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	  // enable skeleton generation for all joints
	  context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
	 
	}
	 
	public void draw()
	{
		context.update();
		//image(context.depthImage(),0,0); 
		
		int i;
		
		for(i=0;i<=10;i++) {
		    // check if the skeleton is being tracked
		    if(context.isTrackingSkeleton(i))
		    {
		      // draw a circle for a head 
		      //drawSkeleton(i); 
		      updateSkelPos(i);
		    }
		}

	}

	
	// draws a circle at the position of the head
	public void updateSkelPos(int userId)
	{
	  // get 3D position of a joint
	  
		
	  PVector jointPos = new PVector();
	  PVector jointPos_Proj = new PVector(); 
	  
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_HEAD,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  tete.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));
		 
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HAND,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  main_droite.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));
		 
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HAND,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  main_gauche.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));
	   
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_NECK,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  cou.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));
	  /*	
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_COLLAR,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.collier_droit,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y),(jointPos_Proj.z));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_COLLAR,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.collier_gauche,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	  */
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_SHOULDER,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  epaule_droite.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_SHOULDER,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  epaule_gauche.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_ELBOW,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  coude_droit.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_ELBOW,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  coude_gauche.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	 /* context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_WRIST,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.poignet_droit,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	 
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_WRIST,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.poignet_gauche,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_FINGERTIP,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.doigt_droit,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_FINGERTIP,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.doigt_gauche,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_PROFILE_UPPER,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.torse_haut,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_PROFILE_LOWER,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  l.actualiser(Skeleton.torse_bas,(int) (context.depthWidth()-jointPos_Proj.x), (int) (jointPos_Proj.y), (jointPos_Proj.z));
	  */
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HIP,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  hanche_droite.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HIP,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  hanche_gauche.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_KNEE,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  genou_droit.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x),(jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_KNEE,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  genou_gauche.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_FOOT,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  pied_droit.setPoint(new Pt((context.depthWidth()-jointPos_Proj.x), (jointPos_Proj.y), (jointPos_Proj.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_FOOT,jointPos);
	  context.convertRealWorldToProjective(jointPos,jointPos_Proj);
	  pied_gauche.setPoint(new Pt( (context.depthWidth()-jointPos_Proj.x),(jointPos_Proj.y), (jointPos_Proj.z)));
	
	  l.actualiser();
	  
	}
	 
	// draw the skeleton with the selected joints
	public void drawSkeleton(int userId)
	{  
	  // draw limbs  
		context.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);
		 
		  context.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_LEFT_SHOULDER);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_LEFT_ELBOW);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW, SimpleOpenNI.SKEL_LEFT_HAND);
		 
		  context.drawLimb(userId, SimpleOpenNI.SKEL_NECK, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_RIGHT_ELBOW);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW, SimpleOpenNI.SKEL_RIGHT_HAND);
		 
		  context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER, SimpleOpenNI.SKEL_TORSO);
		 
		  context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_LEFT_HIP);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP, SimpleOpenNI.SKEL_LEFT_KNEE);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE, SimpleOpenNI.SKEL_LEFT_FOOT);
		 
		  context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO, SimpleOpenNI.SKEL_RIGHT_HIP);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP, SimpleOpenNI.SKEL_RIGHT_KNEE);
		  context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE, SimpleOpenNI.SKEL_RIGHT_FOOT);  
	}
	 
	// Event-based Methods
	 
	// when a person ('user') enters the field of view
	public void onNewUser(int userId)
	{
		System.out.println("New User Detected - userId: " + userId);
	 
	 // start pose detection
	  context.startPoseDetection("Psi",userId);
	}
	 
	// when a person ('user') leaves the field of view 
	public void onLostUser(int userId)
	{
		System.out.println("User Lost - userId: " + userId);
	}
	 
	// when a user begins a pose
	public void onStartPose(String pose,int userId)
	{
		System.out.println("Start of Pose Detected  - userId: " + userId + ", pose: " + pose);
	 
	  // stop pose detection
	  context.stopPoseDetection(userId); 
	 
	  // start attempting to calibrate the skeleton
	  context.requestCalibrationSkeleton(userId, true); 
	}
	 
	// when calibration begins
	public void onStartCalibration(int userId)
	{
		System.out.println("Beginning Calibration - userId: " + userId);
	}
	 
	// when calibaration ends - successfully or unsucessfully 
	public void onEndCalibration(int userId, boolean successfull)
	{
		System.out.println("Calibration of userId: " + userId + ", successfull: " + successfull);
	 
	  if (successfull) 
	  { 
		  System.out.println("  User calibrated !!!");
		  l.detection();
	    // begin skeleton tracking
	    context.startTrackingSkeleton(userId); 
	  } 
	  else 
	  { 
		  System.out.println("  Failed to calibrate user !!!");
	 
	    // Start pose detection
	    context.startPoseDetection("Psi",userId);
	  }
	}

}
