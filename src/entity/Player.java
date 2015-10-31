package entity;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import org.newdawn.slick.*;

import app.ResourceLoader;
import entity.enemies.Enemy;


public class Player extends GameObject implements KeyListener, MouseListener {
    private boolean collision, hit;
    private double dx, dy;

    public boolean shooting = false;
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();

    public void setHit(){
        hit = true;
    }

    public Player(double x, double y){
        super(x, y, Math.toRadians(90));

        width = 65;
        height = 92;

        collisionWidth = 60;
        collisionHeight = 80;

        speed = 3;
        maxHealth = health = 10;
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
            setCrashing();
            hit = false;
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
                 new Laser(x + 10, y - 1, mx, my, this),
                 new Laser(x + 50, y - 1, mx, my, this),
                 new Laser(x + 20, y + 15, mx, my, this),
                 new Laser(x + 40, y + 15, mx, my, this)
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
                if(!enemies.get(i).isCrashing()){
                    collision = true;
                    enemies.get(i).setCrashing();
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
                   weapons.get(j).kill();
                }
            }
        }
        return hit;
    }

    public void keyTyped(java.awt.event.KeyEvent arg0) {}

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void setInput(Input arg0) {
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
                dy = speed;
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
                dy = 0;
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
    }

    @Override
    public void mouseReleased(int arg0, int arg1, int arg2) {
    }

    public void render(org.newdawn.slick.Graphics g) {
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
}
