package test;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageTest {

	public static void main(String[] args) throws IOException {
		File sourceimage = new File("C:\\Users\\daou\\Desktop\\사진\\images.jpg");
		Image image = (Image) ImageIO.read(sourceimage);   
	}

}
