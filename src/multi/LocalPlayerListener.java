package multi;

import entity.Player;
import input.PlayerInputListener;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;

    public LocalPlayerListener(Player player, PlayerSocket socket){
        super(player);
        this.socket = socket;
    }

    LocalPlayerListener() {
        super();
    }

    public void keyPressed(int key, char arg1) {
        super.keyPressed(key, arg1);
        socket.send(new Message(Message.MOVE, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        socket.send(new Message(Message.STOP, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);
        socket.send(new Message(Message.MOVE, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        super.mouseClicked(button, arg1, arg2, arg3);
        socket.send(new Message(Message.SHOOT, (int) player.getX(), (int) player.getY(), player.getAngle()));
    }
}
