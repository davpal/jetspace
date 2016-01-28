package multi;

import java.util.Deque;
import java.util.LinkedList;

public class PacketSender implements Runnable {
    private PlayerSocket socket;
    private boolean running;
    private volatile Deque<Message> messages = new LinkedList<>();

    public PacketSender(PlayerSocket socket) {
        this.socket = socket;
        running = true;
        new Thread(this).start();
    }

    public synchronized void add(Message m){
        messages.add(m);
    }

    @Override
    public void run(){
        while(running) {
            while(!messages.isEmpty()){
                socket.send(messages.poll());
            }
        }
    }
}
