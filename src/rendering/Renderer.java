package rendering;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

import entity.Player;
import entity.Weapon;
import resource.ResourceFactory;

public class Renderer {
    private Graphics g;
    
    private Image playerShip = ResourceFactory.getPlayerShip();
    private Animation explosion = ResourceFactory.getExplosion();

    private boolean explosionRunning;

    public Renderer(Graphics graphics) {
        g = graphics;
    }

    public void renderPlayer(Player player) {
        if (!player.isCrashing()) {
            g.pushTransform();
            g.rotate((float) player.getCenterX(), (float) player.getCenterY(),
                    (float) Math.toDegrees(player.getAngle()));
            g.drawImage(playerShip, (float) player.getX(), (float) player.getY());
            g.popTransform();
            g.pushTransform();
            g.setColor(new Color((10 - player.getHealth()) * 25, player.getHealth() * 25, 0, 220));
            g.fillRect((int) (player.getX() + (player.getWidth() - 50) / 2),
                    (int) (player.getY() + player.getHeight() - 2),
                    5 * player.getHealth(), 5);
            g.popTransform();
        } else {
            if (explosionRunning) {
                explosion.start();
                explosionRunning = true;
            }
            explosion.draw((float) player.getX(), (float) player.getY());
            if (explosion.isStopped()) {
                player.kill();
            }
        }

        for (Weapon w : player.getWeapons()) {
            w.render(g);
        }
    }

    public void render(Weapon w) {

    }
}
