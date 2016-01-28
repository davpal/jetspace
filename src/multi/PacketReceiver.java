package multi;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Deque;
import java.util.LinkedList;

public class PacketReceiver implements Runnable {
    private PlayerSocket socket;
    private boolean running;
    private volatile Deque<Message> messages = new LinkedList<>();

    public PacketReceiver(PlayerSocket socket) {
        this.socket = socket;
        running = true;
        new Thread(this).start();
    }

    public void run() {
        while(running) {
            Message m = socket.receive();
            if(!m.getSource().equals(MultiplayerConfiguration.getInterface()))
                messages.add(m);
        }
    }

    public synchronized Message poll() {
        return messages.poll();
    }
}
