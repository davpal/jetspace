package input;

import menu.multi.InterfaceSelect;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.InputAdapter;

public class MultiSetupListener extends InputAdapter {
    private InterfaceSelect interfaceSelect;

    private Rectangle prevInterface;
    private Rectangle nextInterface;

    public MultiSetupListener(InterfaceSelect is) {
        this.interfaceSelect = is;
        prevInterface = new Rectangle(is.getX() - 60, is.getY(), 40, is.getHeight());
        nextInterface = new Rectangle(is.getX() + is.getWidth() + 20, is.getY(), 40, is.getHeight());
    }

    @Override
    public void mouseClicked(int button, int mx, int my, int count) {
        Rectangle mouse = new Rectangle(mx, my, 1, 1);
        if(mouse.intersects(prevInterface)) {
            interfaceSelect.prev();
        } else if(mouse.intersects(nextInterface)) {
            interfaceSelect.next();
        }
    }
}
