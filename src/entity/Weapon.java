package entity;

import java.awt.Graphics;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;

public abstract class Weapon extends GameObject {
    double angle;
    Point shipPos;
    
    protected Weapon(double x, double y) {
        super(x, y);
    }

    public abstract void update(GameContainer g);

    public void setAngle(double a) {
        angle = a;
    }
    
    public void setShipPosition(Point p){
        shipPos = p;
    }

}