package app;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Player;
import entity.enemies.*;


public class MainClass extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;
	Thread thread = new Thread(this);
	boolean running = true;
	
	Player player;
	
	ArrayList <Enemy> enemies = new ArrayList<Enemy>();
	private Image buffer;
	private BufferedImage background;
	
	public void init(){
		setSize(315, 600);
	
		player = new Player(315 / 2 - 34, 600 - 80);
		background = ResourceLoader.getImage("/backgrounds/level1.png");
		
		enemies.add(new Ship(100, 100));

		this.addKeyListener(player);
	};
	
	public void start(){ thread.start(); }
	public void destroy(){ running = false; }
	public void stop(){ running = false; }
	
	public void run(){
		while(running) {
			
			update(getGraphics());
			repaint();

			try {
				Thread.sleep(20);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void update(Graphics g){
		player.update(this);
		for(Enemy e:enemies){
			e.update(this);
		}
		
		player.checkAttack(enemies);
		
		paint(g);
	}
	
	public void paint(Graphics screen){
		buffer = this.createImage(getWidth(), getHeight());
		Graphics g = buffer.getGraphics();
		
		g.drawImage(background, 0, 0, null);
		
		player.paint(g);
		for(Enemy e:enemies){
			e.paint(g);
		}
		
		screen.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
	}
}