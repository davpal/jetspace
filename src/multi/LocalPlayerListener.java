package multi;

import entity.Player;
import input.PlayerInputListener;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;
    PacketSender sender;
    private ArrayList<Integer> pressedKeys = new ArrayList<>();
    private GameContainer gc;

    public LocalPlayerListener(Player player, PacketSender sender, GameContainer gc){
        super(player);
        this.sender = sender;
        this.gc = gc;
    }

    LocalPlayerListener() {
        super();
    }

    @Override
    public void keyPressed(int key, char arg1) {
        super.keyPressed(key, arg1);
        pressedKeys.add(key);

        double dx = player.getDx();
        double dy = player.getDy();
        for(int k : pressedKeys) {
            switch (k) {
                case Input.KEY_D: {
                    dx = player.getSpeed();
                    break;
                }
                case Input.KEY_A: {
                    dx = -player.getSpeed();
                    break;
                }
                case Input.KEY_W: {
                    dy = -player.getSpeed();
                    break;
                }
                case Input.KEY_S: {
                    dy = player.getSpeed();
                    break;
                }
                default:
                    break;
            }
        }

        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();
        Message move = new Message.Builder().code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(dx, dy)
                              .mousePosition(mx, my)
                              .build();
        sender.send(move);
    }

    @Override
    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        pressedKeys.remove(Integer.valueOf(key));
        double dx = player.getDx();
        double dy = player.getDy();

        switch (key) {
            case Input.KEY_D:
            case Input.KEY_A:
                dx = 0;
                break;
            case Input.KEY_W:
            case Input.KEY_S:
                dy = 0;
                break;
            default:
                break;
        }

        Message stop = new Message.Builder().code(Message.STOP)
                              .pid(player.getPid())
                              .shifts(dx, dy)
                              .position(player.getX(), player.getY())
                              .build();

        sender.send(stop);
    }

    @Override
    public void mouseMoved(int ox, int oy, int nx, int ny) {
        super.mouseMoved(ox, oy, nx, ny);

        //
        // FIXME: nx, ny are not proper values
        // Get mouse posistion in direct way
        //
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();

        Message move = new Message.Builder().code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(player.getDx(), player.getDy())
                              .mousePosition(mx, my)
                              .build();

        sender.send(move);
    }

    @Override
    public void mouseClicked(int button, int arg1, int mx, int my) {
        super.mouseClicked(button, arg1, mx, my);

        Message shoot = new Message.Builder().code(Message.SHOOT)
                               .pid(player.getPid())
                               .position(player.getX(), player.getY())
                               .mousePosition(mx, my)
                               .build();

        sender.send(shoot);
    }
}
