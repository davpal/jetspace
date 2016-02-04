package multi;

import entity.Player;
import org.newdawn.slick.GameContainer;

public class NetworkPlayer extends Player {
    protected double mx, my;

    public NetworkPlayer(String name, double x, double y) {
        super(name, x, y);
    }

    @Override
    public void update(GameContainer gc) {
        angle = -Math.atan2((x + width / 2) - mx, (y + height / 2) - my);

        x += dx;
        y += dy;
    }

    public void setMouseX(int mx) {
        this.mx = mx;
    }

    public void setMouseY(int my) {
        this.my = my;
    }
}
