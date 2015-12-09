package menu.multi;

import java.net.InetAddress;
import menu.MenuItem;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

public class InterfaceSelect extends MenuItem {
    InetAddress address;

    public InterfaceSelect(InetAddress a){
        super(a.toString());
        address = a;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String toString(){
        return address.toString();
    }

    public void execute(StateBasedGame g) {
        g.enterState(3);
    }

    public void render(Renderer r){
        r.renderInterfaceSelect(this);
    }
}
