package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by winio_000 on 2015-10-31.
 */
public class ImageLoader {
    public static org.newdawn.slick.Image loadImage(String path) {
        Image image = null;
        try {
            image = new Image(path);
        } catch (SlickException sE) {
            sE.printStackTrace();
        }
        return image;
    }
}
