package entity;

import app.Game;
import app.ResourceLoader;

public class EnemyLaser extends Laser {
	public EnemyLaser(double d, double e) {
		super(d, e);
		beam = ResourceLoader.getImage("/enemy/laser.png");
	}
	
	public void update(Game mc){
		y += 5;
	}

}
