package multi;

import menu.MenuItem;
import menu.Quit;
import menu.SinglePlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

public class MultiplayerState extends BasicGameState {
    private Renderer renderer;
    
    public MultiplayerState(GameContainer gc) {
        renderer = new Renderer(gc);
    }
    
    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Multiplayer Game", 100, 100);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
}
