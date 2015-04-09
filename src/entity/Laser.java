package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.Game;
import app.ResourceLoader;
public class Laser extends Weapon {
    BufferedImage beam;
    
    Laser(double d, double e) {
        super(d, e);
        collisionWidth = width = 8;
        collisionHeight = height = 30;
        speed = 6;
        
        beam = ResourceLoader.getImage("/player/laser.png");
    }

    public void paint (Graphics g){
        Graphics2D gb = (Graphics2D) g.create();
        gb.rotate(angle, x + width / 2, y + width / 2);
        gb.drawImage(beam, (int)x, (int)y, width, height, null);
    }

    public void update(Game g){
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }
}
