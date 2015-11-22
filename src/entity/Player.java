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

    public void checkCollision(ArrayList<? extends GameObject> enemies) {
        for (int i = 0; i < enemies.size(); ++i) {
            if (enemies.get(i).intersect(this)) {
                if (!enemies.get(i).isCrashing() && !enemies.get(i).isDead()) {
                    collision = true;
                    enemies.get(i).setCrashing();
                }
            }
        }
    }

    public boolean checkAttack(ArrayList<? extends GameObject> enemies) {
        boolean hit = false;
        for (int i = 0; i < enemies.size(); ++i) {
            for (int j = 0; j < weapons.size(); ++j) {
                if (!weapons.get(j).isDead()
                        && weapons.get(j).intersect(enemies.get(i))) {
                    enemies.get(i).setHit(true);
                    weapons.get(j).kill();
                }
            }
        }
        return hit;
    }
}
