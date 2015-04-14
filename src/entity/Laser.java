package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.newdawn.slick.*;

import app.ResourceLoader;
public class Laser extends Weapon {
    Image beam;
    
    Laser(double x, double y, double sx, double sy, double tx, double ty, double angle) {
        super(x, y, sx, sy, tx, ty, angle);
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
        double dx = Math.cos(angle);
        x += dx;
        y += Math.sqrt(speed * speed - dx * dx);
    }

    @Override
    public void render(org.newdawn.slick.Graphics g) {
        System.out.println(angle);
        g.pushTransform();
        g.rotate((float)startX + 65 / 2, (float)startY + 92 / 2, (float)Math.toDegrees(angle));
        g.drawImage(beam, (int)x, (int)y, null);
        g.popTransform();
    }
}
