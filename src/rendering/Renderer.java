package rendering;

import entity.EnemyLaser;
import entity.Laser;
import entity.Player;
import entity.Weapon;
import entity.enemies.Bomber;
import game.JetSpace;
import menu.Menu;
import menu.MenuItem;
import org.newdawn.slick.*;
import resource.ResourceFactory;
import resource.ResourceLoader;

import java.util.ArrayList;
import java.util.Iterator;
import menu.multi.InterfaceSelect;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Renderer {
    private GameContainer gc;
    private Graphics g;

    private Image menuBackground = ResourceLoader.getImage("backgrounds/menu.jpg");
    private Image playerShip = ResourceFactory.getPlayerShip();
    private Image bomberShip = ResourceFactory.getBomberShip();
    private Image playerLaser = ResourceFactory.getPlayerLaser();
    private Image enemyLaser = ResourceFactory.getEnemyLaser();
    private Font titleFont = ResourceLoader.getFont("fonts/modern_caveman.ttf", 48f);
    private Font itemFont  = ResourceLoader.getFont("fonts/modern_caveman.ttf", 28f);
    private Font nameFont = ResourceLoader.getFont("fonts/modern_caveman.ttf", 14f);
    Image cursor = ResourceLoader.getImage("player/crosshair.png");

    private ArrayList<Explosion> explosions = new ArrayList<>();

    public Renderer(GameContainer gc) {
        this.gc = gc;
        this.g = gc.getGraphics();
    }

    public void renderPlayer(Player player) {
        if (!player.isDead()) {
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
            g.setFont(nameFont);
            g.drawString(player.getName(), (float)player.getX() - 10,
               (float)player.getY());
        }

        for (Weapon w : player.getWeapons()) {
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
        if(!bomber.isDead()) {
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

        for (Weapon w : bomber.getWeapons()) w.render(this);
    }
    
    public void renderMenu(Menu menu){
        g.drawImage(menuBackground, 0, 0, gc.getWidth(), gc.getHeight(), 
            0, 0, 1920, 1024);
        g.setColor(new Color(190, 210, 220, 255));
        g.setFont(titleFont);
        g.drawString(JetSpace.TITLE,
                (gc.getWidth() - titleFont.getWidth(JetSpace.TITLE)) / 2, 50);

        Iterator it = menu.iterator();
        while(it.hasNext()){
            MenuItem item = ((MenuItem)it.next());
            item.render(this);
        }
        renderCursor();
    }

    public void renderMenuItem(MenuItem item){
        g.setFont(itemFont);
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        g.setColor(new Color(190, 210, 220, 255));
        if(item.isSelected()){
            g.setColor(new Color(255, 0, 0, 255));
        }
        g.drawRect(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        g.drawString(item.toString(), item.getX() + 20, item.getY() + 10);
    }

    public void renderInterfaceSelect(InterfaceSelect item){
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        g.setColor(new Color(190, 210, 220, 255));
        g.drawRect(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        g.drawString(item.toString(), item.getX() + 20, item.getY() + 10);

        float[] leftt = new float[] {
            item.getX() - 60, item.getY() + item.getHeight() / 2,
            item.getX() - 20, item.getY(),
            item.getX() - 20, item.getY() + item.getHeight(),
        };

        float[] rightt = new float[] {
            item.getX() + item.getWidth() + 20, item.getY(),
            item.getX() + item.getWidth() + 60, item.getY() + item.getHeight() / 2,
            item.getX() + item.getWidth() + 20, item.getY() + item.getHeight()
        };

        g.fill(new Polygon(leftt));
        g.fill(new Polygon(rightt));
    }

    public void renderExplosion(Explosion e) {
        Animation a = e.getAnimation();
        if(!e.done())
            a.draw((float)e.getOwner().getX(), (float)e.getOwner().getY());
    }
}
