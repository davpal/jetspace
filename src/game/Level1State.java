package game;

import java.util.ArrayList;

import entity.ImageLoader;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entity.Player;
import entity.enemies.Bomber;
import entity.enemies.Enemy;
import input.PlayerInputListener;
import rendering.Renderer;

public class Level1State extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Player player;
    private Image background;
    private Renderer renderer;
    
    public Level1State(GameContainer gc){
        renderer = new Renderer(gc);
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        player.update(gc);

        if (!player.isDead()) {
            player.checkCollision(enemies);
            player.checkAttack(enemies);
        } else {
            game.enterState(2);
            return;
        }

        for (int i = 0; i < enemies.size(); ++i) {
            enemies.get(i).checkCollision(enemies);
            if (!player.isDead()) {
                enemies.get(i).fire(player);
                enemies.get(i).checkAttack(player);
            }
            enemies.get(i).faceTo(player);
            enemies.get(i).update(gc);
            if (enemies.get(i).isDead()) {
                enemies.remove(i--);
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) {
        background.draw(0, 0);
        player.render(renderer);
        for(Enemy e:enemies){
            e.render(renderer);
        }
    }

    public boolean isAcceptingInput() {
        return true;
    }


    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {

        background = ImageLoader.loadImage("backgrounds/level1.png");
        Image cursor = ImageLoader.loadImage("player/crosshair.png");
        gc.setMouseCursor(cursor, 16, 16);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        enemies.clear();
        player = new Player(gc.getWidth() / 2 - 100, gc.getHeight() / 2);
        PlayerInputListener playerListener = new PlayerInputListener(player);
        gc.getInput().addKeyListener(playerListener);
        gc.getInput().addMouseListener(playerListener);
        enemies.add(new Bomber(25, 50, 0));
        enemies.add(new Bomber(200, 100, 0));
        enemies.add(new Bomber(100, 150, 0));
    }

    public int getID() {
        return 1;
    }
}
