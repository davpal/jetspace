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

        Message move = builder.code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(dx, dy)
                              .mousePosition(0, 0)
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

        Message stop = builder.code(Message.STOP)
                              .pid(player.getPid())
                              .shifts(dx, dy)
                              .position(player.getX(), player.getY())
                              .mousePosition(0, 0)
                              .build();

        sender.send(stop);
    }

    @Override
    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);

        Message move = builder.code(Message.MOVE)
                              .pid(player.getPid())
                              .shifts(0, 0)
                              .mousePosition(0, 0)
                              .build();

        sender.send(move);
    }

    @Override
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
