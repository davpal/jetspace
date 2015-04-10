package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import org.newdawn.slick.*;

public class GameOverState extends GameState {

    GameOverState(GameContainer g) {
        super(g);
        // TODO Auto-generated constructor stub
    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //game.setState(new MenuState(game));
        gc.getInput().removeAllKeyListeners();
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics g) {
        // TODO Auto-generated method stub
        
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
