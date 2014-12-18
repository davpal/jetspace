package app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Animation;

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
	
	public static Animation getAnimation(String resource, int width, int height, int framesNum, int delay){
		Animation animation = new Animation(width, height);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(
						ResourceLoader.class.getResourceAsStream(resource));
			
			BufferedImage[] frames = new BufferedImage[framesNum];
			for(int i = 0; i < framesNum; ++i){
				frames[i] = image.getSubimage(i % 5 * width, (i % 5) * height, width, height);
			}
			
			animation.setFrames(frames);
			animation.setDelay(delay);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return animation;	
	}
}
