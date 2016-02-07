package game;

import entity.Player;
import entity.enemies.Bomber;
import entity.enemies.Enemy;
import input.PlayerInputListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Explosion;
import rendering.Renderer;
import resource.ResourceLoader;

import java.util.ArrayList;

public class Level1State extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    private Player player;
    private PlayerInputListener playerListener = new PlayerInputListener();
    private Image background;
    private Renderer renderer;
    Audio music;
    
    public Level1State(GameContainer gc){
        this.music = ResourceLoader.getAudio("WAV", "audio/battle.wav");
        renderer = new Renderer(gc);
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if(player.isCrashing()){
            explosions.add(new Explosion(player));
            player.kill();
        }
        if (player.isDead()) {
            if(!explosions.isEmpty()) return;
            lose(game);
        } else {
            player.checkCollision(enemies);
            player.checkAttack(enemies);
            player.update(gc);
        }

        for (int i = 0; i < enemies.size(); ++i) {
            enemies.get(i).checkCollision(enemies);
            if (!player.isDead()) {
                enemies.get(i).fire(player);
                enemies.get(i).checkAttack(player);
            }
            if(enemies.get(i).isCrashing()){
                explosions.add(new Explosion(enemies.get(i)));
                enemies.get(i).kill();
            }
            if (enemies.get(i).isDead()) {
                if(!explosions.isEmpty()) continue;
                enemies.remove(i--);
                if(enemies.isEmpty()) {
                    win(game);
                }
            } else {
                enemies.get(i).faceTo(player);
                enemies.get(i).update(gc);
            }
        }
    }
    
    private void lose(StateBasedGame game){
        game.enterState(2);
        playerListener.disable();
    }
    
    private void win(StateBasedGame game){
        game.enterState(4);
        playerListener.disable();
    }

    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) {
        background.draw(0, 0);
        player.render(renderer);

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

        background = ResourceLoader.getImage("backgrounds/game.jpg");
        gc.setMouseGrabbed(true);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        enemies.clear();
        player = new Player(500, 220);
        playerListener.setPlayer(player);
        playerListener.enable();
        gc.getInput().addKeyListener(playerListener);
        gc.getInput().addMouseListener(playerListener);
        gc.getInput().removeAllListeners();
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
