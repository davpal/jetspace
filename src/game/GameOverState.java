package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {
    private StateBasedGame game;

    GameOverState(StateBasedGame g) {
        game = g;
    }

    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        // TODO Auto-generated method stub
    }

    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        java.awt.Font f = new java.awt.Font("Palatino", java.awt.Font.BOLD, 30);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
        g.setFont(ttf);
        g.drawString("Game Over", (gc.getWidth() - 220) / 2, (gc.getHeight() - 180) / 2);
    }

    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
        // TODO Auto-generated method stub
    }

    @Override
    public int getID() {
        return 2;
    }
}
