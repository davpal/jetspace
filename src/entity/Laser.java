package entity;
import org.newdawn.slick.*;
public class Laser extends Weapon {
    Image beam;
    double dx, dy;
    
    public Laser(double x, double y, GameObject owner) {
        super(x, y, owner);

        collisionWidth = width = 8;
        collisionHeight = height = 30;
        speed = 6;

        double centerX = owner.getCenterX();
        double centerY = owner.getCenterY();

        setCoordinates(x, y, centerX, centerY);
        setDerivatives();
        getBeam();
    }

    private void setCoordinates(double x, double y, double centerX, double centerY) {
        this.x = centerX + (x - centerX) * Math.cos(angle) - (y - centerY) * Math.sin(angle) - width / 2;
        this.y = centerY + (x - centerX) * Math.sin(angle) + (y - centerY) * Math.cos(angle) - height / 2;
    }

    private void setDerivatives() {
        dx = speed * Math.sin(angle);
        dy = -speed * Math.cos(angle);
    }

    private void getBeam() {
	try {
            beam = new Image("player/laser.png");
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
    }

    public Laser(double x, double y, double tx, double ty, GameObject owner) {
        this(x, y, owner);

        double centerX = owner.getCenterX();
        double centerY = owner.getCenterY();
        
        setCoordinates(x, y, centerX, centerY);  

        angle = -Math.atan2(getCenterX() - tx, getCenterY() - ty);
        
        setDerivatives();
    }

    public void update(GameContainer g){
        x += dx;
        y += dy;
        
        if(x > g.getWidth() || y > g.getHeight() || x < 0 || y < 0)
            kill();
    }

    @Override
    public void render(org.newdawn.slick.Graphics g) {
        g.pushTransform();
        g.rotate((float)getCenterX(), (float)getCenterY(), (float)Math.toDegrees(angle));
        g.drawImage(beam, (float)x, (float)y);
        g.popTransform();
    }
}
