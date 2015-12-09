package menu.multi;

import java.net.InetAddress;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.BasicComponent;
import org.newdawn.slick.gui.GUIContext;
import rendering.Renderer;

public class InterfaceSelect extends BasicComponent {
    ArrayList<InetAddress> addresses;
    int selected;

    public InterfaceSelect(GUIContext gc, ArrayList<InetAddress> addresses){
        super(gc);
        this.addresses = addresses;
        this.selected = 0;
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
    public void renderImpl(GUIContext gc, Graphics g) {
    }
}
