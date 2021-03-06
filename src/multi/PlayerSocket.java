package multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PlayerSocket {
    public static final int PACKET_SIZE = 40;
    DatagramSocket socket;

    PlayerSocket(int port) throws Exception {
        socket = new DatagramSocket(port);
    }

    PlayerSocket(int port, InetAddress address) throws Exception {
        socket = new DatagramSocket(port, address);
    }

    public void send(Message message) {
        DatagramPacket datagramPacket = MessageConverter.toPacket(message);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message receive() {
        byte[] data = new byte[PlayerSocket.PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (Exception e) {
            return null;
        }
        return MessageConverter.parsePacket(packet);
    }

    public void close() {
        socket.close();
    }

    public InetAddress getAddress() {
        return socket.getLocalAddress();
    }
}
