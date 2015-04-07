package entity;

import java.awt.Graphics;

import app.Game;

public abstract class Weapon extends GameObject {
    protected Weapon(double x, double y) {
        super(x, y);
    }

    public abstract void paint(Graphics g);

    public abstract void update(Game g);

}