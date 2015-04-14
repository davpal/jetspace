package state;

import java.awt.event.KeyEvent;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class GameOverState extends GameState {

    GameOverState(GameContainer g) {
        super(g);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        gc.getInput().removeAllKeyListeners();
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GameContainer gc, org.newdawn.slick.Graphics g) {
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
}
