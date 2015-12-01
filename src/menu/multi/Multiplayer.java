/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.multi;

import input.MenuInputListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import menu.Menu;
import menu.MenuItem;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author dave
 */
public class Multiplayer extends Menu {
    public Multiplayer() {
        super("Multi player");
                Enumeration<NetworkInterface> nics = null;
        try {
            nics = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
        }

        while (nics.hasMoreElements()) {
            NetworkInterface nic = nics.nextElement();
            try {
                if (nic.isLoopback()) {
                    continue;
                }
            } catch (Exception e) {

            }
            Enumeration<InetAddress> addresses = nic.getInetAddresses();
            if (!addresses.hasMoreElements()) {
                continue;
            }
            InetAddress address = addresses.nextElement();
            if (!(address instanceof Inet4Address)) {
                continue;
            }
            addItem(new InterfaceItem(address));
	}
        addItem(new MenuItem("Back"));
    }
}
