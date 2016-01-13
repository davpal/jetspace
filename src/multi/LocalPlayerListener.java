package multi;

import entity.Player;
import input.PlayerInputListener;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;
    PacketSender sender;

    public LocalPlayerListener(Player player, PacketSender sender){
        super(player);
        this.sender = sender;
    }

    LocalPlayerListener() {
        super();
    }

    public void keyPressed(int key, char arg1) {
        super.keyPressed(key, arg1);
        sender.add(new Message(Message.MOVE, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        sender.add(new Message(Message.STOP, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);
        sender.add(new Message(Message.MOVE, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        super.mouseClicked(button, arg1, arg2, arg3);
        sender.add(new Message(Message.SHOOT, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }
}
