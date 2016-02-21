package multi;

import java.net.InetAddress;

public class MultiplayerConfiguration {
    private MultiplayerConfiguration() {}

    public static final int SEND_PORT = 40000;
    public static final int RECV_PORT = 41000;
    private static InetAddress gameInterface;
    private static String name;

    public static void setInterface(InetAddress i) {
        gameInterface = i;
    }

    public static InetAddress getInterface() {
        return gameInterface;
    }

    public static void setPlayerName(String n){
        name = n;
    }

    public static String getPlayerName(){
        return name;
    }
}
