package state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import app.Game;
import entity.Player;
import entity.enemies.Enemy;
import entity.enemies.Ship;

public class Level1State extends GameState {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Player player;

	Level1State(Game g){
		super(g);

		player = new Player(315 / 2 - 34, 600 - 80);
		enemies.add(new Ship(25, 50));
		enemies.add(new Ship(200, 100));
		enemies.add(new Ship(100, 150));

		g.addKeyListener(player);
	}

	public void update(){
		player.update(game);

		if(!player.isDead()){
		    player.checkCollision(enemies);
			player.checkAttack(enemies);
		}

		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).checkCollision(enemies);
			if(!player.isDead()){
				enemies.get(i).fire(player);
				enemies.get(i).checkAttack(player);
			}
			enemies.get(i).update(game);
			if(enemies.get(i).isDead()){
				enemies.remove(i--);
			}
		}
	}

	public void paint(Graphics g){
		if(!player.isDead()) player.paint(g);
		for(Enemy e:enemies){
			e.paint(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
