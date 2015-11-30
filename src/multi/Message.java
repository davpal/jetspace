package multi;

import java.net.DatagramPacket;

/**
 * Created by winio_000 on 2015-11-29.
 */
public class Message {
    public static final short JOIN = 0x1000;
    public static final short ACCEPT = 0x1001;
    public static final short SYNC = 0x1002;
    public static final short MOVE = 0x1003;
    public static final short SHOOT = 0x1004;
    public static final short HIT = 0x1005;
    public static final short DEAD =  0x1006;
    public static final short QUIT = 0x1007;
    public static final short PICK = 0x1008;
    public static final short MAX_SIZE = 1024;

    private LocalPlayer localPlayer;
    private NetworkPlayer networkPlayer; //TODO many networkplayers


    public Message() {

    }


    public Message parsePacket(DatagramPacket packet) {
        if(packet.getData() != null)
            return new Message();
        return null;
    }
}
