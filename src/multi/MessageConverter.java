package multi;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public final class MessageConverter {
    public static DatagramPacket toPacket(Message message) {
        int size = message.getSize();
        if(message.getCode() == Message.ACCEPT) size += 2;

        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.put(message.getCode());
        buffer.put(message.getPid());

        if(message.getCode() == Message.ACCEPT) {
            buffer.putShort((short)message.getName().length());
            buffer.put(message.getName().getBytes());
        }

        if(message.getCode() != Message.JOIN && message.getCode() != Message.HIT) {
            if(message.getCode() == Message.MOVE) {
                buffer.putInt(message.getDx());
                buffer.putInt(message.getDy());
            } else {
                buffer.putInt(message.getX());
                buffer.putInt(message.getY());
            }
            buffer.putInt(message.getMouseX());
            buffer.putInt(message.getMouseY());
        }

        InetAddress broadcast = null;
        try {
            broadcast = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException ex) {
        }

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(),
                broadcast, MultiplayerConfiguration.RECV_PORT);
        return packet;
    }

    public static Message parsePacket(DatagramPacket packet) {
        Message.Builder builder = new Message.Builder();

        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
        byte code = buffer.get();
        byte pid = buffer.get();
        builder.code(code)
               .pid(pid);

        if(code == Message.ACCEPT) {
            short nameLength = buffer.getShort();
            byte[] nameArray = new byte[nameLength];
            buffer.get(nameArray);
            builder.name(new String(nameArray));
        }

        if(code != Message.JOIN) {
            if(code == Message.MOVE)
                builder.shifts(buffer.getInt(), buffer.getInt());
            else
                builder.position(buffer.getInt(), buffer.getInt());
            builder.mousePosition(buffer.getInt(), buffer.getInt());
        }

        builder.source(packet.getAddress());
        return builder.build();
    }
}
