package entity.enemies;

import entity.GameObject;

public abstract class Enemy extends GameObject  {
	public boolean hit;
	
	Enemy(int x, int y) {
		super(x, y);
	}
	
	public void setHit(){
		hit = true;
	}

	public boolean isHit() {
		return hit;
	}
	
}


