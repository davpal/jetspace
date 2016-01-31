package multi;

import entity.Player;
import org.newdawn.slick.GameContainer;

public class NetworkPlayer extends Player {
    public NetworkPlayer(String name, double x, double y) {
        super(name, x, y);
    }

    @Override
    public void update(GameContainer gc) {
        x += dx;
        y += dy;
    }
}
