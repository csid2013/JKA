package outils;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.*;
   
/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public class Sound {
        // bullet
   private ArrayList<Clip> listeClips;
   
   public Sound() {
	   
	   this.listeClips = new ArrayList<Clip>();
	   this.listeClips.add(getClip("Varia_Menu.wav"));
	   this.listeClips.add(getClip("Varia_1.wav"));
	   this.listeClips.add(getClip("Varia_Boss.wav"));
	   
   }
   
   public Clip getClip(String soundFileName) {
	   
	   Clip clip=null;
	   
	   try {
	         // Use URL (instead of File) to read from disk and JAR.
	         URL url = this.getClass().getClassLoader().getResource(soundFileName);
	         // Set up an audio input stream piped from the sound file.
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         // Get a clip resource.
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioInputStream);
	         clip.loop(Clip.LOOP_CONTINUOUSLY);
	         clip.loop(5);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   
	   return clip;
	   
   }
   
   public void stopAll() {
	   
	   for(int i=0;i<listeClips.size();i++) {
		   if(this.listeClips.get(i).isRunning()) {
		   this.listeClips.get(i).stop();
		   }
	   }
	   
   }
   
   public void playStart() {
	   
	   stopAll();
	   this.listeClips.get(0).start();
	   
   }
   
	public void playHome() {
	   
	   stopAll();
	   this.listeClips.get(1).start();
	   
   }
   
	public boolean aucunSon() {
		
		boolean aucunSon=true;
		
		for(int i=0;i<this.listeClips.size();i++) {
			
			aucunSon = aucunSon&&this.listeClips.get(i).isRunning();
			
		}
		
		return aucunSon;
		
	}
	
	public void playGame() {
		
		stopAll();
		this.listeClips.get(2).start();
		
	}
	
	public boolean isRunning(int i) {
		
		return this.listeClips.get(i).isRunning();
		
	}
	
   
}