package menu.multi;

import java.net.InetAddress;
import menu.MenuItem;
import org.newdawn.slick.state.StateBasedGame;

public class InterfaceItem extends MenuItem {
    InetAddress address;

    public InterfaceItem(InetAddress a){
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
}
