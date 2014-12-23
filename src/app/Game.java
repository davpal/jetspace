package app;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import state.GameState;
import state.MenuState;
import entity.enemies.Enemy;


public class Game extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	Thread thread = new Thread(this);
	boolean running = true;

	private Image buffer;
	private BufferedImage background;

	GameState gameState;

	Game(){
		init();
		start();
	}

	public void init(){
		setSize(315, 600);
		setTitle("JetSpace v0.1");

		//player = new Player(315 / 2 - 34, 600 - 80);
		background = ResourceLoader.getImage("/backgrounds/level1.png");

		//enemies.add(new Ship(100, 100));

		//this.addKeyListener(player);

		setVisible(true);
		setResizable(false);

		gameState = new MenuState();
		addKeyListener(gameState);
	};

	public synchronized void start(){ thread.start(); }
	public synchronized void stop(){ running = false; }

	public void run(){
		while(running) {
			update(getGraphics());
			repaint();

			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void update(Graphics g){
		//player.update(this);
		//for(int i = 0; i < enemies.size(); ++i){
		//	enemies.get(i).update(this);
		//	if(enemies.get(i).isHit()){
		//		enemies.remove(i--);
		//	}
		//}

		//player.checkAttack(enemies);

		paint(g);
	}

	public void paint(Graphics screen){
		buffer = this.createImage(getWidth(), getHeight());
		Graphics g = buffer.getGraphics();

		g.drawImage(background, 0, 0, null);

		//player.paint(g);
		//for(Enemy e:enemies){
		//	e.paint(g);
		//}

		gameState.paint(g);

		screen.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args){
		new Game();
	}
}