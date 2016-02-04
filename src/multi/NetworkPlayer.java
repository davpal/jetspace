package multi;

import entity.Laser;
import entity.Player;
import java.util.Arrays;
import org.newdawn.slick.GameContainer;

public class NetworkPlayer extends Player {
    protected double mx, my;

    public NetworkPlayer(String name, double x, double y) {
        super(name, x, y);
    }

    @Override
    public void update(GameContainer g) {
        angle = -Math.atan2((x + width / 2) - mx, (y + height / 2) - my);

        x += dx;
        y += dy;

        checkMapBounds(g);
        shootLasers(g);
        updateWeapons(g);
    }

    public void setMouseX(int mx) {
        this.mx = mx;
    }

    public void setMouseY(int my) {
        this.my = my;
    }

    @Override
    public void shootLasers(GameContainer gc) {
        if (isShooting()) {
            Laser[] lasers = new Laser[]{
                    new Laser(x + 10, y - 1, mx, my, this),
                    new Laser(x + 50, y - 1, mx, my, this),
                    new Laser(x + 20, y + 15, mx, my, this),
                    new Laser(x + 40, y + 15, mx, my, this)};

            weapons.addAll(Arrays.asList(lasers));

            setShooting(false);
        }
    }
}
