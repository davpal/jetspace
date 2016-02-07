package entity.enemies;

import entity.EnemyLaser;
import entity.Player;
import entity.Weapon;
import org.newdawn.slick.GameContainer;
import rendering.Renderer;

import java.util.ArrayList;
import java.util.Random;

public class Bomber extends Enemy {
    private int fireRate;
    private boolean shooting;
    private long shootTime;
    
    private ArrayList<Weapon> lasers = new ArrayList<>();
  
    public ArrayList<Weapon> getWeapons(){
        return lasers;
    }

    public Bomber(double x, double y, double a) {
        super(x, y, a);

        width = 79;
        height = 68;
        collisionWidth = 70;
        collisionHeight = 60;
        
        speed = 0.2;
        health = 10;
        fireRate = 1000;

        down = true;
        left = true;
    }

    public void render(Renderer r) {
        r.renderBomber(this);
    }

    public void fire(Player p) {
        long elapsed = (System.nanoTime() - shootTime) / 1000000;
        Random rand = new Random();
        if (elapsed > rand.nextInt() % 200 + 1500) {
            shooting = true;
            shootTime = System.nanoTime();
        }
    }

    public void update(GameContainer g) {
        shoot();
        updateWeapons(g);
        checkAttack();
        checkCollision();
        move(g);
        destroyOutside(g);
    }

    private void checkAttack() {
        if (health <= 0) {
            setCrashing();
            setHit(false);
        }

        if (isHit()) {
            health -= 1;
            setHit(false);
        }
    }

    private void move(GameContainer g) {
        if (right) x += speed;
        else if (left) x -= speed;
        if (down) y += speed;

        if (x > g.getWidth() - width) {
            left = true;
            right = false;
        } else if (x < 0) {
            right = true;
            left = false;
        }
    }

    private void checkCollision() {
        if (collision) {
            collision = false;

            if (right) {
                left = true;
                right = false;
            } else if (left) {
                left = false;
                right = true;
            }

            if (up) {
                down = true;
                up = false;
            } else if (down) {
                down = false;
                up = true;
            }
        }
    }

    private void updateWeapons(GameContainer g) {
        for (int i = 0; i < lasers.size(); ++i) {
            lasers.get(i).update(g);
            if (lasers.get(i).isDead()) {
                lasers.remove(i--);
            }
        }
    }

    private void shoot() {
        if(shooting) {
            lasers.add(new EnemyLaser(x + 4, y + 30, this));
            lasers.add(new EnemyLaser(x + 68, y + 30, this));
            shooting = false;
        }
    }

    @Override
    public void checkAttack(Player player) {
        for(int i = 0; i < lasers.size(); ++i){
            if(lasers.get(i).intersect(player)){
                player.setHit(true);
                lasers.get(i).kill();
            }
        }
    }
}
