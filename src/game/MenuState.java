package game;

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
    Menu menu = new Menu();
    Renderer renderer;
    StateBasedGame game;
    Audio music;
    
    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        MenuItem singlePlayer = new SinglePlayer();
        singlePlayer.select();
        menu.addItem(singlePlayer);
        menu.addItem(new Multiplayer());
        menu.addItem(new Options());
        menu.addItem(new Quit());
        music = ResourceLoader.getAudio("WAV", "audio/battle.wav");
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
        music.playAsMusic(1f, 1f, true);
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
