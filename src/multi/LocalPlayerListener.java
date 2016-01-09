package multi;

import entity.Player;
import input.PlayerInputListener;

public class LocalPlayerListener extends PlayerInputListener {
    PlayerSocket socket;

    public LocalPlayerListener(Player player){
        super(player);
    }

    public LocalPlayerListener(PlayerSocket socket){
        this.socket = socket;
    }

    LocalPlayerListener() {
        super();
    }

    public void keyPressed(int key, char arg1) {
        super.keyPressed(key, arg1);
        // TODO: Send MOVE Message
    }

    public void keyReleased(int key, char arg1) {
        super.keyReleased(key, arg1);
        // TODO: Send STOP Message
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        super.mouseMoved(arg0, arg1, mx, my);
        // TODO: Send MOVE Message
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        super.mouseClicked(button, arg1, arg2, arg3);
        // TODO: Send SHOOT Message
    }
}
