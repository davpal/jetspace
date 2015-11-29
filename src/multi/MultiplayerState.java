package multi;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import menu.MenuItem;
import menu.Quit;
import menu.SinglePlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

public class MultiplayerState extends BasicGameState {
    private Renderer renderer;
    
    public MultiplayerState(GameContainer gc) {
        renderer = new Renderer(gc);
    }
    
    @Override
    public int getID() {
        return 3;
    }

    ArrayList<InetAddress> ips = new ArrayList<>();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
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
            ips.add(address);
	}
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Multiplayer Game", 100, 100);
        for(int i = 0; i < ips.size(); ++i){
            g.drawString(ips.get(i).toString(), 100, 150 + i * 50);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
}
