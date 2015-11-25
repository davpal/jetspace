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
import org.newdawn.slick.openal.Audio;
import rendering.Explosion;
import resource.ResourceLoader;

public class Level1State extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    private ControlledPlayer controlledPlayer;
    private Image background;
    private Renderer renderer;
    Audio music;
    
    public Level1State(GameContainer gc){
        this.music = ResourceLoader.getAudio("WAV", "audio/battle.wav");
        renderer = new Renderer(gc);
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if(controlledPlayer.isCrashing()){
            explosions.add(new Explosion(controlledPlayer));
            controlledPlayer.kill();
        }
        if (controlledPlayer.isDead()) {
            if(!explosions.isEmpty()) return;
            game.enterState(2);
            return;
        } else {
            controlledPlayer.checkCollision(enemies);
            controlledPlayer.checkAttack(enemies);
            controlledPlayer.update(gc);
        }

        for (int i = 0; i < enemies.size(); ++i) {
            enemies.get(i).checkCollision(enemies);
            if (!controlledPlayer.isDead()) {
                enemies.get(i).fire(controlledPlayer);
                enemies.get(i).checkAttack(controlledPlayer);
            }
            if(enemies.get(i).isCrashing()){
                explosions.add(new Explosion(enemies.get(i)));
                enemies.get(i).kill();
            }
            if (enemies.get(i).isDead()) {
                if(!explosions.isEmpty()) continue;
                enemies.remove(i--);
                if(enemies.isEmpty()) {
                    game.enterState(2);
                    return;
                }
            } else {
                enemies.get(i).faceTo(controlledPlayer);
                enemies.get(i).update(gc);
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) {
        background.draw(0, 0);
        controlledPlayer.render(renderer);

        for(Enemy e:enemies){
            e.render(renderer);
        }
        for(int i = 0; i < explosions.size(); ++i){
            if(explosions.get(i).done()) explosions.remove(i--);
            else explosions.get(i).render(renderer);
        }
        renderer.renderCursor();
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {

        background = ImageLoader.loadImage("backgrounds/game.jpg");
        gc.setMouseGrabbed(true);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        enemies.clear();
        controlledPlayer = new ControlledPlayer(500, 220);
        PlayerInputListener playerListener = new PlayerInputListener(controlledPlayer);
        gc.getInput().addKeyListener(playerListener);
        gc.getInput().addMouseListener(playerListener);
        enemies.add(new Bomber(50, 50, 0));
        enemies.add(new Bomber(50, 150, 0));
        enemies.add(new Bomber(50, 250, 0));
        enemies.add(new Bomber(250, 100, 0));
        enemies.add(new Bomber(250, 200, 0));
        enemies.add(new Bomber(250, 300, 0));
    }

    public int getID() {
        return 1;
    }
}
