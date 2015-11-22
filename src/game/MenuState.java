package game;

import menu.Menu;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;
import resource.ResourceLoader;
import menu.*;

public class MenuState extends BasicGameState {
    Menu menu = new Menu();
    Renderer renderer;
    StateBasedGame game;
    
    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        MenuItem singlePlayer = new SinglePlayer();
        singlePlayer.select();
        menu.addItem(singlePlayer);
        menu.addItem(new MenuItem("Multi player"));
        menu.addItem(new MenuItem("Options"));
        menu.addItem(new Quit());
        
        renderer = new Renderer(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game1, Graphics g)
            throws SlickException {
        renderer.renderMenu(menu);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_RETURN) {
            Command command = (Command)menu.getSelected();
            command.execute(game);
        } else if (key == Input.KEY_DOWN)
            menu.nextItem();
        else if (key == Input.KEY_UP)
            menu.prevItem();
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
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
        return 0;
    }
}
