package game;

import java.awt.event.KeyEvent;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {
    private StateBasedGame game;
    
    GameOverState(StateBasedGame g) {
        game = g;
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        game.enterState(0);
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(int arg0, char arg1) {
    }

    @Override
    public void keyReleased(int arg0, char arg1) {
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void setInput(Input arg0) {
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 2;
    }
}
