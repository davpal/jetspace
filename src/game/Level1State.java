package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entity.Player;
import entity.enemies.Bomber;
import entity.enemies.Enemy;

public class Level1State extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Player player;
    private Image background;
    
    public Level1State(GameContainer gc){
        player = new Player(gc.getWidth() / 2 - 100, gc.getHeight() / 2);
        enemies.add(new Bomber(25, 50, 0));
        enemies.add(new Bomber(200, 100, 0));
        enemies.add(new Bomber(100, 150, 0));
        gc.getInput().addKeyListener(player);
        gc.getInput().addMouseListener(player);
        
        try {
            background = new Image("backgrounds/level1.png");
            Image cursor = new Image("player/crosshair.png");
            gc.setMouseCursor(cursor, 16, 16);
        } catch(SlickException e){
            
        }  
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta){
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
            enemies.get(i).faceTo(player);
            enemies.get(i).update(gc);
            if(enemies.get(i).isDead()){
                enemies.remove(i--);
            }
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) {
        background.draw(0, 0);
        if(!player.isDead()) player.render(g);
        for(Enemy e:enemies){
            e.render(g);
        }
    }
    
    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(int arg0, char arg1) {  
    }

    public void keyReleased(int arg0, char arg1) {     
    }

    public void inputEnded() {  
    }

    public void inputStarted() {    
    }
    
    public boolean isAcceptingInput() {
        return true;
    }
    
    public void setInput(Input arg0) {    
    }

    public void init(GameContainer arg0, StateBasedGame arg1)
            throws SlickException {   
    }

    public int getID() {
        return 0;
    }
}