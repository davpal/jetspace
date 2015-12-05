package menu;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Fullscreen extends MenuItem {
    public Fullscreen() {
        super("Fullscreen");
    }

    @Override
    public void execute(StateBasedGame g) {
        boolean fullscreen = g.getContainer().isFullscreen();
        try {
            g.getContainer().setFullscreen(!fullscreen);
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
}
