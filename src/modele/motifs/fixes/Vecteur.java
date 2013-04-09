/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.motifs.fixes;

/**
 *
 * @author rayanox
 */
public class Vecteur {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private final double longueurVecteurParfait = 14.1421356237;
    //********************************************
    
    public Vecteur(int x1, int y1, int x2, int y2) {
        this.x1= x1;
        this.y1 = y1;
        this.x2= x2;
        this.y2 = y2;
    }
    
    public Vecteur() {
        this.x1= 0;
        this.y1 = 0;
        this.x2= 0;
        this.y2 = 0;
    }
    
    public void translationVecteur() {
        
        int AC = this.getX2()-this.getX1();
        int CB = this.getY2()-this.getY1();
        
        double k = this.coefficientMultiplicateur(AC, CB);
        
        this.x1=0;
        this.y1=0;
        this.x2=(int) (AC*k);
        this.y2=(int) (CB*k);
        		
        		
    }
    
    public double coefficientMultiplicateur(int AC, int BC) {
        double k = (this.longueurVecteurParfait/ (Math.sqrt((AC*AC) + (BC*BC))));
        return k;
    }
    
    //*********************************************
    
    public void setX1(int x1) {
        this.x1 = x1;
    }
    
    public void setY1(int y1) {
        this.y1 = y1;
    }
    
    public int getX1() {
        return this.x1;
    }
    
    public int getY1() {
        return this.y1;
    }
    
    public void setX2(int x2) {
        this.x2 = x2;
    }
    
    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    public int getX2() {
        return this.x2;
    }
    
    public int getY2() {
        return this.y2;
    }
}
