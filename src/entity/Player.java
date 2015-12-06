package entity;

import org.newdawn.slick.GameContainer;
import rendering.Renderer;

import java.util.ArrayList;

public class Player extends GameObject {
    protected boolean collision;
    protected int health, maxHealth;
    protected boolean shooting = false;
    protected ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    public Player(double x, double y) {
        super(x, y, Math.toRadians(90));

        width = 65;
        height = 92;

        collisionWidth = 60;
        collisionHeight = 80;

        speed = 3;
        maxHealth = health = 10;
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

    public void update(GameContainer g) {
        if (collision) {
            collision = false;
            setHit(true);
        }

        if (isHit()) {
            health -= 1;
            setHit(false);
        }

        if (health <= 0) {
            setCrashing();
            hit = false;
        }

        x += dx;
        y += dy;

        checkMapBounds(g);
        shootLasers(g);
        updateWeapons(g);
    }

    public void checkMapBounds(GameContainer g){
        if (x < 0)
            x = 0;
        else if (x > g.getWidth() - width - 20)
            x = g.getWidth() - width - 20;

        if (y < 0)
            y = 0;
        else if (y > g.getHeight() - height)
            y = g.getHeight() - height;
    }

    public void shootLasers(GameContainer g){
        double mx = g.getInput().getMouseX();
        double my = g.getInput().getMouseY();

        angle = -Math.atan2((x + width / 2) - mx, (y + height / 2) - my);

        if (isShooting()) {
            Laser[] lasers = new Laser[]{
                    new Laser(x + 10, y - 1, mx, my, this),
                    new Laser(x + 50, y - 1, mx, my, this),
                    new Laser(x + 20, y + 15, mx, my, this),
                    new Laser(x + 40, y + 15, mx, my, this)};

            for (Weapon w : lasers) {
                weapons.add(w);
            }

            setShooting(false);
        }
    }

    public void updateWeapons(GameContainer g){
        for (int i = 0; i < weapons.size(); ++i) {
            weapons.get(i).update(g);
            if (weapons.get(i).isDead()) {
                weapons.remove(i--);
            }
        }
    }

    public void render(Renderer r) {
        r.renderPlayer(this);
    }
}
