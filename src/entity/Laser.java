package entity;
import org.newdawn.slick.*;
import rendering.Renderer;

public class Laser extends Weapon {
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
    }

    private void setCoordinates(double x, double y, double centerX, double centerY) {
        this.x = centerX + (x - centerX) * Math.cos(angle) - (y - centerY) * Math.sin(angle) - width / 2;
        this.y = centerY + (x - centerX) * Math.sin(angle) + (y - centerY) * Math.cos(angle) - height / 2;
    }

    private void setDerivatives() {
        dx = speed * Math.sin(angle);
        dy = -speed * Math.cos(angle);
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

    public void render(Renderer r) {
        r.renderPlayerLaser(this);
    }
}
