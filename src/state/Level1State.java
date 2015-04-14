package state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import entity.Player;
import entity.enemies.Bomber;
import entity.enemies.Enemy;

public class Level1State extends GameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Player player;
    
    public Level1State(GameContainer gc){
        super(gc);
        
        player = new Player(gc.getWidth() / 2 - 100, gc.getHeight() / 2);
        enemies.add(new Bomber(25, 50));
        enemies.add(new Bomber(200, 100));
        enemies.add(new Bomber(100, 150));
        
        gc.getInput().addKeyListener(player);
        gc.getInput().addMouseListener(player);
    }
    
    public void update(){
        player.update(gc);
        
        if(!player.isDead()){
            player.checkCollision(enemies);
            player.checkAttack(enemies);
        } else {
            gc.getInput().removeAllKeyListeners();
        }
        
        for(int i = 0; i < enemies.size(); ++i){
            enemies.get(i).checkCollision(enemies);
            if(!player.isDead()){
                enemies.get(i).fire(player);
                enemies.get(i).checkAttack(player);
            }
            enemies.get(i).update(gc);
            if(enemies.get(i).isDead()){
                enemies.remove(i--);
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics g) {
        if(!player.isDead()) player.render(g);
        for(Enemy e:enemies){
            e.render(g);
        }
    }

    @Override
    public void keyPressed(int arg0, char arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(int arg0, char arg1) {
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setInput(Input arg0) {
        // TODO Auto-generated method stub
        
    }
}
