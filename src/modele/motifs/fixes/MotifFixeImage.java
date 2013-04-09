package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;




public class MotifFixeImage extends MotifFixeRectangle {

	private Image image;

	public MotifFixeImage(ArrayList<PtSkeleton> listePoints, Color couleur,
			int largeur, int hauteur, String imagePath) {
		super(listePoints, couleur, largeur, hauteur);

		try {
			this.setImage(ImageIO.read(new File(imagePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}

}
