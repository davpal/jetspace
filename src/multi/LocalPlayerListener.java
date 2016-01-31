package multi;

import entity.Player;
import input.PlayerInputListener;
import java.util.ArrayList;
import org.newdawn.slick.Input;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;
    PacketSender sender;
    private ArrayList<Integer> pressedKeys = new ArrayList<>();
    private MessageBuilder builder = new MessageBuilder();

    public LocalPlayerListener(Player player, PacketSender sender){
        super(player);
        this.sender = sender;
    }

    LocalPlayerListener() {
        super();
    }

    public void keyPressed(int key, char arg1) {
        super.keyPressed(key, arg1);
        pressedKeys.add(key);

        double dx = 0, dy = 0;
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
                    dx = dy = 0;
            }
        }

        Message move = builder.code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(dx, dy)
                              .mousePosition(0, 0)
                              .build();
        sender.send(move);
    }

    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        pressedKeys.remove(Integer.valueOf(key));

        Message stop = builder.code(Message.STOP)
                              .pid(player.getPid())
                              .position(player.getX(), player.getY())
                              .mousePosition(0, 0)
                              .build();

        sender.send(stop);
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);

        Message move = builder.code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(0, 0)
                              .mousePosition(0, 0)
                              .build();

        sender.send(move);
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        super.mouseClicked(button, arg1, arg2, arg3);

        Message shoot = builder.code(Message.SHOOT)
                               .pid(player.getPid())
                               .position(player.getX(), player.getY())
                               .mousePosition(0, 0)
                               .build();

        sender.send(shoot);
    }
}
