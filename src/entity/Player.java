package entity;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.enemies.Enemy;
import app.Game;
import app.ResourceLoader;


public class Player extends GameObject implements KeyListener {
    private boolean collision, hit, crash;
    private double dx, dy;
    private int health, maxHealth;
    
    Animation explosion;
    
    private BufferedImage ship;
    
    public boolean shooting = false;
    private ArrayList<Laser> lasers;

    public void setHit(){
        explosion.setPosition(x, y);
        hit = true;
    }
    
    public Player(int x, int y){
        super(x, y);
        
        width = 79;
        height = 68;
        
        collisionWidth = 58;
        collisionHeight = 60;
        
        speed = 3;
        maxHealth = health = 10;
        
        ship = ResourceLoader.getImage("/player/ship.png");
        explosion = ResourceLoader.getAnimation("/enemy/explosion.png", 64, 64, 5, 50);
        
        lasers = new ArrayList<Laser>();
    }

    public void paint (Graphics g)
    {
        if(!crash){
            g.drawImage(ship, (int)x, (int)y, null);
            g.setColor(new Color((10 - health) * 25, health * 25, 0, 220));
            g.fillRect((int)x + (width - 50) / 2, (int)y + height - 2, 5 * health, 5);
        }
        else {
            explosion.paint(g);
        }
        
        for(Laser l:lasers)
            l.paint(g);
    }

    public void update(Game g){
        if(collision){
            collision = false;
            setHit();
        }
        
        if(hit){
            health -= 1;
            hit = false;
        }
        
        if(health <= 0){
            crash = true;
            explosion.update();
            if(explosion.hasPlayedOnce()){
                dead = true;
                hit = false;
            }
        }
        
        x += dx;
        y += dy;    
        
        if(x < 0) x = 0;
        else if(x > 315 - width - 10) x = 315 - width - 10;
        
        if(y < 0) y = 0;
        else if(y > 600 - height) y = 600 - height;
        
        if(shooting)
        {
            lasers.add(new Laser(x + 4, y - 2));
            lasers.add(new Laser(x + 68, y - 2));
            
            shooting = false;
        }
        
        for(int i = 0; i < lasers.size(); ++i){
            lasers.get(i).update(g);
            if(lasers.get(i).isDead()){
                lasers.remove(i--);
            }
        }
    }
    
    public void checkCollision(ArrayList<Enemy> enemies){
        for(int i = 0; i < enemies.size(); ++i){
            if(enemies.get(i).intersect(this)){
                if(!enemies.get(i).isCrash()){
                    collision = true;
                    enemies.get(i).setCrash();
                }
            }
        }
    }
    
    public boolean checkAttack(ArrayList<Enemy> enemies){
        boolean hit = false;
        for(int i = 0; i < enemies.size(); ++i){
            for(int j = 0; j < lasers.size(); ++j){
                if(!lasers.get(j).isDead() && lasers.get(j).intersect(enemies.get(i))){
                    enemies.get(i).setHit();
                    lasers.get(j).setDead();
                }
            }
        }
        return hit;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                dx = speed;
                break;
            }
            case KeyEvent.VK_LEFT:{
                dx = -speed;
                break;                
            }
            case KeyEvent.VK_UP:{
                dy = -speed;
                break;
            }                    
            case KeyEvent.VK_DOWN:{
                dy= speed;
                break;
            }                
            case KeyEvent.VK_SPACE:{
                shooting = true;
                break;    
            }        
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                dx=0;
                break;        
            }
            case KeyEvent.VK_LEFT:{
                dx=0;
                break;
            }                    
            case KeyEvent.VK_UP:{
                dy=0;
                break;
            }
            case KeyEvent.VK_DOWN:{
                dy=0;
                break;
            }
            
        }
            
    }
        
    public void keyTyped(KeyEvent arg0) {}

    public int getWidth() {
        return width;
    }
}