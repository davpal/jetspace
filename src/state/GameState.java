package state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.KeyListener;

import org.newdawn.slick.*;

public abstract class GameState implements KeyListener {
    GameContainer game;
    GameContainer gc;
    
    public GameState(GameContainer gc) {
        this.gc = gc;
    }

    public abstract void update();
    public abstract void render(GameContainer gc, Graphics g); 
}