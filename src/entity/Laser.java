package entity;
import org.newdawn.slick.*;
public class Laser extends Weapon {
    Image beam;
    double sourceX, sourceY, dx, dy;
    
    public Laser(double x, double y, GameObject owner) {
        super(x, y, owner);
        collisionWidth = width = 8;
        collisionHeight = height = 30;
        speed = 6;
        
        sourceX = owner.getX();
        sourceY = owner.getY();
        
        double centerX = sourceX + owner.getWidth() / 2;
        double centerY = sourceY + owner.getHeight() / 2;
   
        this.x = centerX + (x-centerX)*Math.cos(angle) - 
                 (y-centerY)*Math.sin(angle) - width / 2;
        this.y = centerY + (x-centerX)*Math.sin(angle) + 
                 (y-centerY)*Math.cos(angle) - height / 2;
        
        dx = speed * Math.sin(angle);
        dy = - speed * Math.cos(angle);
        
        System.out.println((int)dx + " " + (int)dy + ", angle = " + Math.toDegrees(angle));
        
        try {
            beam = new Image("player/laser.png");
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
    }

    public void update(GameContainer g){
        x += dx;
        y += dy;
    }

    @Override
    public void render(org.newdawn.slick.Graphics g) {
        g.pushTransform();
        g.rotate((float)x + width / 2, (float)y + height / 2, (float)Math.toDegrees(angle));
        g.drawImage(beam, (float)x, (float)y);
        g.popTransform();
    }
}
