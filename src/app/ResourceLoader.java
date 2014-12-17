package app;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	public static BufferedImage getImage(String resource){
		BufferedImage image = null;
		try {
			image = ImageIO.read(
						ResourceLoader.class.getResourceAsStream(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
