package multi;

import input.MenuInputListener;
import menu.multi.InterfaceMenu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;
import resource.ResourceLoader;

public class MultiplayerSetupState extends BasicGameState {
    private Renderer renderer;
    private InterfaceMenu menu = new InterfaceMenu();
    private Font font = ResourceLoader.getFont("fonts/modern_caveman.ttf", 28f);
    private Image menuBackground = ResourceLoader.getImage("backgrounds/menu.jpg");
    TextField t;

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
        g.drawImage(menuBackground, 0, 0, gc.getWidth(), gc.getHeight(),
            0, 0, 1920, 1024);
        g.setFont(font);
        g.drawString("Enter your name:", 160, 100);

        g.drawString("Choose interface:", 160, 250);
        t.render(gc, g);
        renderer.renderCursor();
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        gc.getInput().removeAllListeners();
        t = new TextField(gc, font, 160, 160, 300, 49);
        t.setBorderColor(Color.red);
        t.setFocus(true);
        MenuInputListener menuListener = new MenuInputListener(menu);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
}
