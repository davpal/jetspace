package entity;

public abstract class Weapon extends GameObject {
    protected GameObject owner;
    
    protected Weapon(double x, double y, double a, GameObject o) {
        super(x, y, a);
        owner = o;
    }
}