package entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

import app.ResourceLoader;
import entity.enemies.Enemy;


public class Player extends GameObject implements KeyListener, MouseMotionListener, MouseListener {
    private boolean collision, hit, crash;
    private double dx, dy;
    private int health, maxHealth;
    double angle;

    Animation explosion;

    private Image ship;

    public boolean shooting = false;
    private ArrayList<Weapon> weapons;

    public void setHit(){
        explosion.setPosition(x, y);
        hit = true;
    }

    public Player(int x, int y){
        super(x, y);

        width = 65;
        height = 92;

        collisionWidth = 60;
        collisionHeight = 80;

        speed = 3;
        maxHealth = health = 10;

        try {
            ship = new Image("player/ship.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        explosion = ResourceLoader.getAnimation("/enemy/explosion.png", 64, 64, 5, 50);

        weapons = new ArrayList<Weapon>();
    }

    public void paint (Graphics g){
        
    }

    public void update(GameContainer g){
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
        else if(x > g.getWidth() - width - 20) x = g.getWidth() - width - 20;

        if(y < 0) y = 0;
        else if(y > g.getHeight() - height) y = g.getHeight() - height;
        
        double mx = g.getInput().getMouseX();
        double my = g.getInput().getMouseY();
        
        angle = -Math.atan2((x + width / 2) - mx, 
                (y + height / 2) - my);

        if(shooting)
        {
            Laser[] lasers = new Laser[]{
                 new Laser(x + 4, y - 2, x, y, mx, my, angle),
                 new Laser(x + 48, y - 2, x, y, mx, my, angle),
                 new Laser(x + 14, y + 6, x, y, mx, my, angle),
                 new Laser(x + 38, y + 6, x, y, mx, my, angle),
            };
            
            for(Weapon w:lasers){
                weapons.add(w);
            }

            shooting = false;
        }

        for(int i = 0; i < weapons.size(); ++i){
            weapons.get(i).update(g);
            if(weapons.get(i).isDead()){
                weapons.remove(i--);
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
            for(int j = 0; j < weapons.size(); ++j){
                if(!weapons.get(j).isDead() && weapons.get(j).intersect(enemies.get(i))){
                   enemies.get(i).setHit();
                   weapons.get(j).setDead();
                }
            }
        }
        return hit;
    }

    public void keyTyped(java.awt.event.KeyEvent arg0) {}

    public int getWidth() {
        return width;
    }

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void mouseMoved(MouseEvent m) {
        // TODO Auto-generated method stub
        angle = -Math.atan2((x + width / 2) - m.getX(), 
                (y + height / 2)- m.getY());
        
    }

    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputEnded() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputStarted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void setInput(Input arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
        shooting = true;
    }

    @Override
    public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        angle = -Math.atan2((x + width / 2) - mx, 
            (y + height / 2) - my);
    }

    @Override
    public void mouseWheelMoved(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(int key, char arg1) {
        switch(key){
            case Input.KEY_D:{
                dx = speed;
                break;
            }
            case Input.KEY_A:{
                dx = -speed;
                break;
            }
            case Input.KEY_W:{
                dy = -speed;
                break;
            }
            case Input.KEY_S:{
                dy= speed;
                break;
            }
            case Input.KEY_SPACE:{
                shooting = true;
                break;
            }
        }
    }
    
    @Override
    public void keyReleased(int key, char arg1) {
        switch(key){
            case Input.KEY_D:{
                dx = 0;
                break;
            }
            case Input.KEY_A:{
                dx = 0;
                break;
            }
            case Input.KEY_W:{
                dy = 0;
                break;
            }
            case Input.KEY_S:{
                dy= 0;
                break;
            }
            case Input.KEY_SPACE:{
                shooting = false;
                break;
            }
        }     
    }

    @Override
    public void mousePressed(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    

    public void render(org.newdawn.slick.Graphics g) {
        if(!crash){
            g.pushTransform();
            g.rotate((float)x + width / 2, (float)y + height / 2, (float)Math.toDegrees(angle));
            g.drawImage(ship, (float)x, (float)y);
            g.popTransform();
            g.pushTransform();
            g.setColor(new Color((10 - health) * 25, health * 25, 0, 220));
            g.fillRect((int)x + (width - 50) / 2, (int)y + height - 2, 5 * health, 5);
            g.popTransform();
        }
        else {
            //explosion.paint(g);
        }

        for(Weapon w:weapons)
            w.render(g);
    }
}
