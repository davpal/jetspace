package multi;

import entity.ImageLoader;
import entity.Player;
import entity.enemies.Bomber;
import entity.enemies.Enemy;
import input.PlayerInputListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import rendering.Explosion;
import rendering.Renderer;
import resource.ResourceLoader;

public class MultiplayerState extends BasicGameState {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    private Player player;
    private PlayerInputListener playerListener = new LocalPlayerListener();
    private Image background;
    private Renderer renderer;
    Audio music;

    public MultiplayerState(GameContainer gc) {
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

        background = ImageLoader.loadImage("backgrounds/game.jpg");
        gc.setMouseGrabbed(true);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame game) throws SlickException {
        player = new Player(MultiplayerConfiguration.getPlayerName(), 500, 220);

        PlayerSocket socket = null;
        try {
            socket = new PlayerSocket(MultiplayerConfiguration.SEND_PORT,
                MultiplayerConfiguration.getInterface());
            socket.send(new Message(Message.JOIN, (int)player.getX(), (int)player.getY(), player.getAngle()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        playerListener = new LocalPlayerListener(player, socket);
        playerListener.enable();

        gc.getInput().removeAllMouseListeners();
        gc.getInput().addListener(playerListener);
    }

    public int getID() {
        return 10;
    }
}
