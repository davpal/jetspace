package rendering;

import entity.EnemyLaser;
import entity.Laser;
import entity.Player;
import entity.Weapon;
import entity.enemies.Bomber;
import game.JetSpace;
import java.util.ArrayList;
import menu.Menu;
import menu.MenuItem;
import org.newdawn.slick.*;
import resource.ResourceFactory;
import resource.ResourceLoader;

import java.util.Iterator;

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

        g.setFont(itemFont);
        
        int position = (gc.getHeight() - 100) / 2;
        Iterator it = menu.iterator();
        while(it.hasNext()){
            g.setColor(new Color(0, 0, 0, 100));
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
        renderCursor();
    }

    public void renderExplosion(Explosion e) {
        Animation a = e.getAnimation();
        if(!e.done())
            a.draw((float)e.getOwner().getX(), (float)e.getOwner().getY());
    }
}
