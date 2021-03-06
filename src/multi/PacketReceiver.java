package multi;

import java.util.Deque;
import java.util.LinkedList;

public class PacketReceiver implements Receiver, Runnable {
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
            if(m != null && !m.getSource().equals(MultiplayerConfiguration.getInterface()))
                messages.add(m);
        }
    }

    @Override
    public synchronized Message receive() {
        return messages.poll();
    }

    public void stop() {
        running = false;
        socket.close();
    }
}
