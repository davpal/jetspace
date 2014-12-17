package entity.enemies;

import entity.GameObject;

public abstract class Enemy extends GameObject  {
	boolean left, right;
	
	Enemy(int x, int y) {
		super(x, y);
		
		right = true;
	}
	
	
}


