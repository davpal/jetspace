package entity;

import app.MainClass;
import app.ResourceLoader;

public class EnemyLaser extends Laser {
	public EnemyLaser(double d, double e) {
		super(d, e);
		beam = ResourceLoader.getImage("/enemy/laser.png");
	}
	
	public void update(MainClass mc){
		y += 5;
	}

}
