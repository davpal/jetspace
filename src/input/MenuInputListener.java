package input;

import menu.Menu;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;
import resource.ResourceFactory;

import java.awt.*;

/**
 * Created by winio_000 on 2015-11-29.
 */
public class MenuInputListener extends InputAdapter {

    private Audio shootEffect = ResourceFactory.getLaserSound();
    private Menu menu;
    private StateBasedGame game;
    private int mouseX;
    private int mouseY;
    private boolean selected = false;
    private Rectangle mouseRectangle;
    private Rectangle singlePlayerRectangle;
    private Rectangle multiPlayerRectangle;
    private Rectangle optionsRectangle;
    private Rectangle quitRectangle;

    public MenuInputListener() {

    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        shootEffect.playAsSoundEffect(1f, 1f, false);
        if (selected) {
            selected = false;
            menu.getSelected().execute(game);
        }
    }

    @Override
    public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
        setMouseCoordinates();
        setMenuOptionsBoundaries();
        chooseOption();
    }

    private void chooseOption() {
        if (mouseRectangle.intersects(singlePlayerRectangle)) {
            menu.setSelected(0);
            menu.getSelected().select();
            selected = true;
        } else if (mouseRectangle.intersects(multiPlayerRectangle)) {
            menu.setSelected(1);
            menu.getSelected().select();
            selected = true;
        } else if (mouseRectangle.intersects(optionsRectangle)) {
            menu.setSelected(2);
            menu.getSelected().select();
            selected = true;
        } else if (mouseRectangle.intersects(quitRectangle)) {
            menu.setSelected(3);
            menu.getSelected().select();
            selected = true;
        } else {
            menu.getSelected().deselect();
            selected = false;
        }
    }

    private void setMenuOptionsBoundaries() {
        singlePlayerRectangle = new Rectangle(160, 271, 300, 49);
        multiPlayerRectangle = new Rectangle(160, 211, 300, 49);
        optionsRectangle = new Rectangle(160, 151, 300, 49);
        quitRectangle = new Rectangle(160, 91, 300, 49);
    }

    private void setMouseCoordinates() {
        mouseX = Mouse.getX();
        mouseY = Mouse.getY();
        mouseRectangle = new Rectangle((int) mouseX, (int) mouseY, 1, 1);
    }

    public void setGame(StateBasedGame game) {
        this.game = game;
    }
}
