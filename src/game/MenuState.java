package game;

import menu.multi.Multiplayer;
import input.MenuInputListener;
import menu.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;
import resource.ResourceLoader;

public class MenuState extends BasicGameState {
    private Menu menu;
    private Renderer renderer;
    private StateBasedGame game;
    private Audio music;
    private MenuInputListener menuInputListener = new MenuInputListener();

    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        music = ResourceLoader.getAudio("WAV", "audio/battle.wav");
        renderer = new Renderer(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game1, Graphics g)
            throws SlickException {
        renderer.renderMenu(menu);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame arg1)
            throws SlickException {
        music.playAsMusic(1f, 1f, true);
        menu = MenuFactory.createMainMenu(gc.getWidth(), gc.getHeight());
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
            throws SlickException {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        container.getInput().addMouseListener(menuInputListener);
        menuInputListener.setMenu(menu);
        menuInputListener.setGame(game);
    }
}
