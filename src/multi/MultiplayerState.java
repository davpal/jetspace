package multi;

import entity.Player;
import entity.enemies.Enemy;
import input.PlayerInputListener;
import java.util.ArrayList;
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
    private NetworkPlayer networkPlayer;
    private PlayerInputListener playerListener = new LocalPlayerListener();
    private Image background;
    private Renderer renderer;
    Audio music;
    private PacketReceiver receiver;
    private boolean waiting = true;

    private PlayerSocket sendSocket;
    private MessageBuilder builder = new MessageBuilder();

    public MultiplayerState(GameContainer gc) {
                this.music = ResourceLoader.getAudio("WAV", "audio/battle.wav");
        renderer = new Renderer(gc);
    }

    public void processMessage(Message m) {
        // Debug received message
        System.out.println("========================================");
        System.out.println("====           RECEIVED             ====");
        System.out.println("====    CODE: " + m.getCode());
        System.out.println("====                                ====");
        System.out.println("========================================");

        switch(m.getCode()) {
            case Message.ACCEPT:
                networkPlayer = new NetworkPlayer("REMOTE", 300, 200);
                break;
            case Message.JOIN:
                player.setX(300);
                player.setY(200);
                networkPlayer = new NetworkPlayer("REMOTE", 200, 300);

                Message accept = builder.code(Message.ACCEPT)
                                        .pid(player.getPid())
                                        .name(player.getName())
                                        .position(player.getX(), player.getY())
                                        .mousePosition(0, 0)
                                        .build();

                sendSocket.send(accept);
                break;
            case Message.MOVE:
                networkPlayer.setDx(m.getDx());
                networkPlayer.setDy(m.getDy());
                networkPlayer.setMouseX(m.getMouseX());
                networkPlayer.setMouseY(m.getMouseY());
                break;
            case Message.SHOOT:
                break;
            case Message.HIT:
                break;
            case Message.STOP:
                networkPlayer.setX(m.getX());
                networkPlayer.setY(m.getY());
                networkPlayer.setDx(0);
                networkPlayer.setDy(0);
                networkPlayer.setMouseX(m.getMouseX());
                networkPlayer.setMouseY(m.getMouseY());
                break;
            case Message.QUIT:
                break;
            default:
        }
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if(waiting) return;

        Message m = receiver.receive();
        if(m != null) {
            processMessage(m);
        }

        networkPlayer.update(gc);

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
        networkPlayer.render(renderer);

        if(!waiting) {
            for(Enemy e:enemies){
                e.render(renderer);
            }
            for(int i = 0; i < explosions.size(); ++i){
                if(explosions.get(i).done()) explosions.remove(i--);
                else explosions.get(i).render(renderer);
            }
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
        player = new Player(MultiplayerConfiguration.getPlayerName(), 200, 300);
        PlayerSocket recvSocket = null;
        try {
            sendSocket = new PlayerSocket(MultiplayerConfiguration.SEND_PORT,
                MultiplayerConfiguration.getInterface());

            Message join = builder.code(Message.JOIN).pid(player.getPid()).build();

            sendSocket.send(join);

            recvSocket = new PlayerSocket(MultiplayerConfiguration.RECV_PORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        receiver = new PacketReceiver(recvSocket);

        Message m = receiver.receive();
        System.out.println("Waiting for players...");
        while(m == null) {
            m = receiver.receive();
        }

        processMessage(m);

        playerListener = new LocalPlayerListener(player, new PacketSender(sendSocket));
        playerListener.enable();
        gc.getInput().removeAllMouseListeners();
        gc.getInput().addListener(playerListener);

        waiting = false;
    }

    public int getID() {
        return 10;
    }
}
