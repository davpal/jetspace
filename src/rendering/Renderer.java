package rendering;

import entity.ControlledPlayer;
import entity.EnemyLaser;
import entity.Laser;
import entity.Weapon;
import entity.enemies.Bomber;
import menu.Menu;
import menu.MenuItem;
import org.newdawn.slick.*;
import resource.ResourceFactory;
import resource.ResourceLoader;

import java.util.Iterator;

public class Renderer {
    private GameContainer gc;
    private Graphics g;
    
    private Image menuBackground = ResourceLoader.getImage("backgrounds/menu.png");
    private Image playerShip = ResourceFactory.getPlayerShip();
    private Image bomberShip = ResourceFactory.getBomberShip();
    private Image playerLaser = ResourceFactory.getPlayerLaser();
    private Image enemyLaser = ResourceFactory.getEnemyLaser();
    private Animation explosion = ResourceFactory.getExplosion();
    private Font titleFont = ResourceLoader.getFont("fonts/modern_caveman.ttf", 36f);
    private Font itemFont  = ResourceLoader.getFont("fonts/modern_caveman.ttf", 28f);
    Image cursor = ResourceLoader.getImage("player/crosshair.png");

    private boolean explosionRunningForEnemy;
    private boolean explosionRunningForPlayer;

    public Renderer(GameContainer gc) {
        this.gc = gc;
        this.g = gc.getGraphics();
    }

    public void renderPlayer(ControlledPlayer controlledPlayer) {
        if (!controlledPlayer.isCrashing()) {
            g.pushTransform();
            g.rotate((float) controlledPlayer.getCenterX(), (float) controlledPlayer.getCenterY(),
                    (float) Math.toDegrees(controlledPlayer.getAngle()));
            g.drawImage(playerShip, (float) controlledPlayer.getX(), (float) controlledPlayer.getY());
            g.popTransform();
            g.pushTransform();
            g.setColor(new Color((10 - controlledPlayer.getHealth()) * 25, controlledPlayer.getHealth() * 25, 0, 220));
            g.fillRect((int) (controlledPlayer.getX() + (controlledPlayer.getWidth() - 50) / 2),
                    (int) (controlledPlayer.getY() + controlledPlayer.getHeight() - 2),
                    5 * controlledPlayer.getHealth(), 5);
            g.popTransform();
        } else {
            if (explosionRunningForPlayer) {
                explosion.start();
                explosionRunningForPlayer = true;
            }
            explosion.draw((float) controlledPlayer.getX(), (float) controlledPlayer.getY());
            if (explosion.isStopped()) {
                controlledPlayer.kill();
            }
        }

        for (Weapon w : controlledPlayer.getWeapons()) {
            w.render(this);
        }
    }

    public void renderCursor(){
        cursor.draw(gc.getInput().getMouseX() - 14, gc.getInput().getMouseY() - 14);
    }
    
    public void renderPlayerLaser(Laser laser){
        renderLaser(laser, playerLaser);
    }

    public void renderEnemyLaser(EnemyLaser laser){
        renderLaser(laser, enemyLaser);
    }

    protected void renderLaser(Laser laser, Image beam){
        g.pushTransform();
        g.rotate((float)laser.getCenterX(), (float)laser.getCenterY(), 
            (float)Math.toDegrees(laser.getAngle()));
        g.drawImage(beam, (float)laser.getX(), (float)laser.getY());
        g.popTransform();
    }

    public void renderBomber(Bomber bomber){
        if(!bomber.isCrashing()) {
            g.pushTransform();
            g.rotate((float) bomber.getCenterX(), (float) bomber.getCenterY(),
                    (float) Math.toDegrees(bomber.getAngle()));
            g.drawImage(bomberShip, (float) bomber.getX(), (float) bomber.getY());
            g.popTransform();
            g.pushTransform();
            g.setColor(new Color((10 - bomber.getHealth()) * 25, bomber.getHealth() * 25, 0, 230));
            g.fillRect((int) (bomber.getX() + (bomber.getWidth() - 50) / 2),
                    (int) (bomber.getY() + bomber.getHeight() - 2),
                    5 * bomber.getHealth(), 5);
            g.popTransform();
        }
        else {
            if (explosionRunningForEnemy) {
                explosion.start();
                explosionRunningForEnemy = true;
            }
            explosion.draw((float) bomber.getX(), (float) bomber.getY());
            if (explosion.isStopped()) {
                bomber.kill();
            }
        }

        for (Weapon w : bomber.getWeapons()) w.render(this);
    }
    
    public void renderMenu(Menu menu){
        menuBackground.draw(0, 0);
        g.setColor(new Color(190, 210, 220, 255));
        g.setFont(titleFont);
        g.drawString("JetSpace v0.2",
                (gc.getWidth() - titleFont.getWidth("JetSpace v0.2")) / 2, (gc.getHeight() - 400) / 2);

        g.setFont(itemFont);

        int position = (gc.getHeight() - 100) / 2;
        Iterator it = menu.iterator();
        while(it.hasNext()){
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.setColor(new Color(190, 210, 220, 255));
            MenuItem item = ((MenuItem)it.next());
            if(item.isSelected()){
                g.setColor(new Color(255, 0, 0, 255));
            }
            g.drawRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.drawString(item.toString(), (gc.getWidth() - 250) / 2, position - 10);
            position += 60;
        }
    }
}
