package entity;

public abstract class Weapon extends GameObject {
    double angle;
    private GameObject owner;
    
    protected Weapon(double x, double y, double a, GameObject o) {
        super(x, y);
        angle = a;
        owner = o;
    }
}