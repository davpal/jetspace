/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

/**
 *
 * @author dave
 */
class MessageConverter {
    public static DatagramPacket toPacket(Message message) {
        int size = message.getSize();
        if(message.getCode() == Message.ACCEPT) size += 2;

        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.put(message.getCode());

        if(message.getCode() != Message.JOIN) {
            buffer.put(message.getPid());
        }

        if(message.getCode() == Message.ACCEPT) {
            buffer.putShort((short)message.getName().length());
            buffer.put(message.getName().getBytes());
        }

        if(message.getCode() != Message.JOIN) {
            buffer.putInt(message.getX());
            buffer.putInt(message.getY());
            buffer.putInt(message.getMouseX());
            buffer.putInt(message.getMouseY());
        }

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        return packet;
    }

    public static Message parsePacket(DatagramPacket packet) {
        MessageBuilder builder = new MessageBuilder();

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
            builder.position(buffer.getInt(), buffer.getInt());
            builder.mousePosition(buffer.getInt(), buffer.getInt());
        }

        builder.source(packet.getAddress());
        return builder.build();
    }
}
