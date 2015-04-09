package state;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import app.Game;

public abstract class GameState implements KeyListener {
    Game game;
    
    GameState(Game g){
        game = g;
    }
    
    public abstract void paint(Graphics g);
    public abstract void update();
}