package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.newdawn.slick.*;

import app.ResourceLoader;
public class Laser extends Weapon {
    Image beam;
    
    Laser(double d, double e) {
        super(d, e);
        collisionWidth = width = 8;
        collisionHeight = height = 30;
        speed = 6;
        
        try {
            beam = new Image("player/laser.png");
        } catch (SlickException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void update(GameContainer g){
        x += Math.cos(angle);
        y += x * Math.sin(angle);
    }

    @Override
    public void render(org.newdawn.slick.Graphics g) {
        g.pushTransform();
        g.rotate((float)shipPos.getX() + 65 / 2, (float)shipPos.getY() + 92 / 2, (float)Math.toDegrees(angle));
        g.drawImage(beam, (int)x, (int)y, null);
        g.popTransform();
    }
}
