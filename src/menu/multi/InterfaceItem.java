package menu.multi;

import menu.MenuItem;
import org.newdawn.slick.state.StateBasedGame;

import java.net.InetAddress;

public class InterfaceItem extends MenuItem {
    InetAddress address;

    public InterfaceItem(InetAddress a){
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
