package resource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class ResourceLoader {
    public static org.newdawn.slick.Image getImage(String path) {
        Image image = null;
        try {
            image = new Image(path);
        } catch (SlickException sE) {
            sE.printStackTrace();
        }
        return image;
    }

    public static Animation getAnimation(String resource, int width, int height, int framesNum, int delay){
        Animation animation = null;
        
        Texture t = null;
        try {
            t = TextureLoader.getTexture("PNG",
                    org.newdawn.slick.util.ResourceLoader
                   .getResourceAsStream(resource));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Image image = new Image(t);
        
        SpriteSheet sprite = new SpriteSheet(image, width, height);
        
        animation = new Animation(sprite, delay);
        animation.setAutoUpdate(true);
        
        return animation;    
    }
}
