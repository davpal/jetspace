package entity;

import java.util.ArrayList;

public abstract class Player extends GameObject {
    protected boolean collision;
    protected int health, maxHealth;
    protected boolean shooting = false;
    protected ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    protected Player(double d, double e, double a) {
        super(d, e, a);
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean s) {
        shooting = s;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public int getHealth() {
        return health;
    }
}
