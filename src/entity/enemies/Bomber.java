package entity.enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entity.Animation;
import entity.EnemyLaser;
import entity.Laser;
import entity.Player;

import org.newdawn.slick.*;

import app.ResourceLoader;

public class Bomber extends Enemy {
	Image skin;
	Animation explosion;
	boolean shooting;
	int fireRate;

	ArrayList<EnemyLaser> lasers = new ArrayList<EnemyLaser>();
	private long shootTime;

	public Bomber(int x, int y){
		super(x, y);

		collisionWidth = 60;
		width = 79;
		collisionHeight = 48;
		height = 68;
		speed = 0.5;
		health = 10;
		fireRate = 1000;

		try {
            skin = new Image("enemy/bomber.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		explosion = ResourceLoader.getAnimation("/enemy/explosion.png", 64, 64, 5, 50);

		down = true;
		left = true;
	}

	public void render(Graphics g) {
		if(crash) ;//explosion.paint(g);
		else {
		    g.pushTransform();
			g.drawImage(skin, (float)x, (float)y);
			g.popTransform();
			g.pushTransform();
			g.setColor(new Color((10 - health) * 25, health * 25, 0, 230));
            g.fillRect((int)x + (width - 50) / 2, (int)y - 10, 5 * health, 5);
            g.popTransform();
		}
		for(Laser l:lasers) l.render(g);
	}

	public void setHit(){
	     hit = true;
	}

	public void fire(Player p){
		long elapsed = (System.nanoTime() - shootTime) / 1000000;
		Random rand = new Random();
		if(elapsed > rand.nextInt() % 200 + 1500){
			if(inFireRange(p))
				shooting = true;
			shootTime = System.nanoTime();
		}
	}

	private boolean inFireRange(Player p) {
		return (x >= p.getX() && x <= p.getX() + p.getWidth() ||
			p.getX() >= x && p.getX() <= x + width) && y < p.getY();
	}

	public void update(GameContainer g) {
		explosion.setPosition(x, y);
		if(shooting){
			lasers.add(new EnemyLaser(x + 4, y + 30, x, y, 0, 0, 0));
			lasers.add(new EnemyLaser(x + 68, y + 30, x, y, 0, 0, 0));

			shooting = false;
		}

		for(int i = 0; i < lasers.size(); ++i){
			lasers.get(i).update(g);
			if(lasers.get(i).isDead()){
				lasers.remove(i--);
			}
		}

		if(health <= 0){
			crash = true;
			explosion.update();
			if(explosion.hasPlayedOnce()){
				dead = true;
				hit = false;
			}
		}

		if(hit){
			health -= 1;
			hit = false;
		}

		if(collision){
			collision = false;

			if(right){
				left = true;
				right = false;
			} else if(left){
				left = false;
				right = true;
			}

			if(up){
				down = true;
				up = false;
			} else if(down){
				down = false;
				up = true;
			}
		}

		if(right) x += speed;
		else if(left) x -= speed;
		if(down) y += speed;

		if(x > g.getWidth() - width){
			left = true;
			right = false;
		} else if(x < 0){
			right = true;
			left = false;
		}

		if(y > 600) setDead();
	}

	@Override
	public void checkAttack(Player player) {
		for(int i = 0; i < lasers.size(); ++i){
			if(lasers.get(i).intersect(player)){
				player.setHit();
				lasers.get(i).setDead();
			}
		}
	}
}
