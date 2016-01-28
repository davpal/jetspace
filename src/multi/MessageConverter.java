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
        ByteBuffer buffer = ByteBuffer.allocate(message.getSize());
        buffer.put(message.getCode());
        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.limit());
        return packet;
    }
}
