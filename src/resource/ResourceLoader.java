package resource;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import rendering.Explosion;

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

    public static org.newdawn.slick.Font getFont(String path, float size){
        Font font = null;
        try {
            InputStream inputStream =
                org.newdawn.slick.util.ResourceLoader.getResourceAsStream(path);
            java.awt.Font awtFont =
                java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            font = new TrueTypeFont(awtFont.deriveFont(size), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return font;
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

    static Audio getAudio(String format, String path) {
        Audio sound = null;
        try {
            sound = AudioLoader.getAudio(format, 
                org.newdawn.slick.util.ResourceLoader.getResourceAsStream(path));
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return sound;
    }
}
