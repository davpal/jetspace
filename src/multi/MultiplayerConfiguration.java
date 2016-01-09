package multi;

import java.net.InetAddress;

public class MultiplayerConfiguration {
    public static final int SEND_PORT = 40000;
    public static final int RECV_PORT = 41000;
    private static InetAddress gameInterface;

    public static void setInterface(InetAddress i) {
        gameInterface = i;
    }

    public static InetAddress getInterface() {
        return gameInterface;
    }
}
