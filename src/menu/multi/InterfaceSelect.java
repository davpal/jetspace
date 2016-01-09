package menu.multi;

import java.net.InetAddress;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import rendering.Renderer;

public class InterfaceSelect extends AbstractComponent {
    ArrayList<InetAddress> addresses;
    int selected;
    private int x, y, width, height;

    public InterfaceSelect(GUIContext gc, int x, int y, int width, int height, ArrayList<InetAddress> addresses){
        super(gc);
        this.addresses = addresses;
        this.selected = 0;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public InetAddress getAddress() {
        return addresses.get(selected);
    }

    public String toString(){
        return addresses.get(selected).toString();
    }

    public void prev(){
        --selected;
        if(selected < 0) selected = addresses.size() - 1;
    }

    public void next(){
        ++selected;
        if(selected > addresses.size() - 1) selected = 0;
    }

    public void render(Renderer r){
        r.renderInterfaceSelect(this);
    }

    @Override
    public void render(GUIContext guic, Graphics grphcs) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
