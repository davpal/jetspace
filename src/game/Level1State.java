package game;

import entity.ControlledPlayer;
import entity.ImageLoader;
import entity.enemies.Bomber;
import entity.enemies.Enemy;
import input.PlayerInputListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

import java.util.ArrayList;

public class Level1State extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ControlledPlayer controlledPlayer;
    private Image background;
    private Renderer renderer;
    
    public Level1State(GameContainer gc){
        renderer = new Renderer(gc);
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        controlledPlayer.update(gc);

        if (!controlledPlayer.isDead()) {
            controlledPlayer.checkCollision(enemies);
            controlledPlayer.checkAttack(enemies);
        } else {
            game.enterState(2);
            return;
        }

        for (int i = 0; i < enemies.size(); ++i) {
            enemies.get(i).checkCollision(enemies);
            if (!controlledPlayer.isDead()) {
                enemies.get(i).fire(controlledPlayer);
                enemies.get(i).checkAttack(controlledPlayer);
            }
            enemies.get(i).faceTo(controlledPlayer);
            enemies.get(i).update(gc);
            if (enemies.get(i).isDead()) {
                enemies.remove(i--);
                if(enemies.isEmpty()) {
                    game.enterState(2);
                    return;
                }
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) {
        background.draw(0, 0);
        controlledPlayer.render(renderer);

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
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        enemies.clear();
        controlledPlayer = new ControlledPlayer(gc.getWidth() / 2 - 100, gc.getHeight() / 2);
        PlayerInputListener playerListener = new PlayerInputListener(controlledPlayer);
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
