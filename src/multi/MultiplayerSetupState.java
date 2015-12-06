package multi;

import input.MenuInputListener;
import menu.multi.InterfaceMenu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

public class MultiplayerSetupState extends BasicGameState {
    private Renderer renderer;
    private InterfaceMenu menu = new InterfaceMenu();

    public MultiplayerSetupState(GameContainer gc) {
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
        renderer.renderCursor();
        renderer.renderMenu(menu);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        MenuInputListener menuListener = new MenuInputListener(menu);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
}
