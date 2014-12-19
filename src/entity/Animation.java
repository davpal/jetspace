package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private BufferedImage[] frames;
	private int currentFrame;
	private long startTime;
	private long delay;
	double x;
	private double y;
	
	private boolean playedOnce;
	private int height;
	private int width;
	
	public Animation(int width, int height){
		this.width = width;
		this.height = height;
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames){
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
				
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void setFrame(int i){
		currentFrame = i;
	}
	
	public void update() {
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay){
			++currentFrame;
			startTime = System.nanoTime();
		}
		
		if(currentFrame == frames.length){
			currentFrame = 0;
			playedOnce = true;
		}
	}
	
	public int getFrame() {
		return currentFrame;
	}
	
	public BufferedImage getImage(){
		return frames[currentFrame];
	}
	
	public boolean hasPlayedOnce() {
		return playedOnce;
	}
	
	public void setPosition(double x2, double y2){
		this.x = x2;
		this.y = y2;
	}
	
	public void paint(Graphics g){
		g.drawImage(frames[currentFrame], (int)x, (int)y, width, height, null);
	}
}
