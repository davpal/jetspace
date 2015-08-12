package entity;
import org.newdawn.slick.*;
public class Laser extends Weapon {
    Image beam;
    double sourceX, sourceY, dx, dy;
    
    public Laser(double x, double y, GameObject owner) {
        super(x, y, owner);
        collisionWidth = width = 8;
        collisionHeight = height = 30;
        speed = 4;
        
        sourceX = owner.getX();
        sourceY = owner.getY();
        
        dx = Math.cos(angle);
        dy = -Math.sqrt(speed * speed - dx * dx);
        
        System.out.println((int)dx + " " + (int)dy);
        
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
        g.drawRect((float)x, (float)y, (float)collisionWidth, (float)collisionHeight);
        g.pushTransform();
        g.rotate((float)sourceX + 65 / 2, (float)sourceY + 92 / 2, (float)Math.toDegrees(angle));
        g.drawImage(beam, (float)x, (float)y);
        g.popTransform();
    }
}
