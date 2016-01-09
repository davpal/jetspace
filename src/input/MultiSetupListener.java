package input;

import menu.MenuItem;
import menu.multi.InterfaceSelect;
import multi.MultiplayerConfiguration;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.InputAdapter;

public class MultiSetupListener extends InputAdapter {
    private InterfaceSelect interfaceSelect;
    private StateBasedGame game;

    private Rectangle prevInterface;
    private Rectangle nextInterface;
    private Rectangle start;

    public MultiSetupListener(InterfaceSelect is, MenuItem s) {
        this.interfaceSelect = is;
        prevInterface = new Rectangle(is.getX() - 60, is.getY(), 40, is.getHeight());
        nextInterface = new Rectangle(is.getX() + is.getWidth() + 20, is.getY(), 40, is.getHeight());
        start = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
    }

    @Override
    public void mouseClicked(int button, int mx, int my, int count) {
        Rectangle mouse = new Rectangle(mx, my, 1, 1);
        if(mouse.intersects(prevInterface)) {
            interfaceSelect.prev();
        } else if(mouse.intersects(nextInterface)) {
            interfaceSelect.next();
        } else if(mouse.intersects(start)) {
            // TODO: Multiplayer game entry point here
            MultiplayerConfiguration.setInterface(interfaceSelect.getAddress());
            game.enterState(10);
        }
    }

    public void setGame(StateBasedGame game) {
        this.game = game;
    }
}
