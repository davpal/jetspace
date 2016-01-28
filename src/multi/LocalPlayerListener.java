package multi;

import entity.Player;
import input.PlayerInputListener;
import java.util.ArrayList;
import org.newdawn.slick.Input;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;
    PacketSender sender;
    private ArrayList<Integer> pressedKeys = new ArrayList<>();

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

        sender.add(new Message(Message.MOVE, (int)dx, (int)dy, player.getAngle()));
    }

    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        pressedKeys.remove(Integer.valueOf(key));
        sender.add(new Message(Message.STOP, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);
        sender.add(new Message(Message.MOVE, (int) 0, (int) 0, player.getAngle()));
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        super.mouseClicked(button, arg1, arg2, arg3);
        sender.add(new Message(Message.SHOOT, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }
}
