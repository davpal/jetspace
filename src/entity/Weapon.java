package entity;

public abstract class Weapon extends GameObject {
    double angle;
    double startX, startY, targetX, targetY;
    
    protected Weapon(double x, double y, double sx, double sy, double tx, double ty, double a) {
        super(x, y);
        startX = sx;
        startY = sy;
        targetX = tx;
        targetY = ty;
        angle = a;
    }
}