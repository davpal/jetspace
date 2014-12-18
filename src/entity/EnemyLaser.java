package entity;

import app.MainClass;
import app.ResourceLoader;

public class EnemyLaser extends Laser {
	public EnemyLaser(int x, int y) {
		super(x, y);
		beam = ResourceLoader.getImage("/enemy/laser.png");
	}
	
	public void update(MainClass mc){
		y += 5;
	}

}
