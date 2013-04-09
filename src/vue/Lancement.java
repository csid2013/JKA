/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outils.Alphabet;

import modele.Personnage;
import modele.deplacements.Interaction;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.fixes.SkeletonMaker;

/**
 *
 * @author ytiab
 */
public class Lancement extends JFrame  {

    protected Toile toile;
    protected Alphabet alphabet ;
    protected SkeletonMaker skel;
    protected JTextField mot;
    protected int nbGeneration;
    protected boolean repeindreTout;
    protected Interaction deplacement;
    
    public Lancement(int depthWidth, int depthHeight, ArrayList<PtSkeleton> listePtSkel) throws MidiUnavailableException  {
        
    			super("Art 3D");
    			System.out.println("lancement: "+listePtSkel);
    			skel = new SkeletonMaker(listePtSkel);
                toile = new Toile(800,600,listePtSkel);
                Personnage pers = new Personnage(skel.getSqueletteHerbin());
    			toile.setListeMotifs(pers);
                this.setSize(new Dimension(815,675));
                JPanel pan = new JPanel();
                mot = new JTextField(20);
                mot.addActionListener(new EcouteurText());
                JButton generer = new JButton("Peindre");
                generer.addActionListener(new EcouteurGenerer());
                JButton repeindre = new JButton("Repeindre ?");
                repeindre.addActionListener(new EcouteurRepeindre()); 
                pan.setBackground(Color.gray);
                pan.add(mot);
                pan.add(generer);
                pan.add(repeindre);
                pan.add(toile);
                this.getContentPane().add(pan);
                this.setVisible(true);
                this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
                

    }
    
    public Interaction getDeplacement() {
    	return this.deplacement;
    }

    
  public void actualiser() {
	 
	  
	  this.toile.repaint();
	  this.repaint();
  }
  
  
  
 public void detection() {
	 toile.detection();
 }
    private class EcouteurRepeindre implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            toile.repaint();
        }

       
    }
    
    private class EcouteurGenerer implements ActionListener  {
                    
        public void actionPerformed(ActionEvent e) {
                
        String texte = mot.getText();
        skel.startText(texte);
        
            }
            
        }
    
        private class EcouteurText implements ActionListener  {
                    
            public void actionPerformed(ActionEvent e) {
                
                
            }
        }
    
}
