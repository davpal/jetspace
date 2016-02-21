package entity;

public abstract class Weapon extends GameObject {
    protected GameObject owner;
    
    protected Weapon(double x, double y, GameObject o) {
        super(x, y, o.getAngle());
        this.owner = o;
    }
}