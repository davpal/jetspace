package entity.enemies;

import java.util.ArrayList;

import entity.GameObject;

public abstract class Enemy extends GameObject  {
	public boolean hit, collision;
	int health;
	
	Enemy(int x, int y) {
		super(x, y);
	}
	
	public void setHit(){
		hit = true;
	}

	public boolean isHit() {
		return hit;
	}

	public void setCollision() {
		collision = true;		
	}
	
	public void checkCollision(ArrayList<Enemy> enemies){
		for(int i = 0; i < enemies.size(); ++i){
			if(!enemies.get(i).equals(this)){
				if(this.intersect(enemies.get(i))) collision = true;
			}
		}
	}
}


