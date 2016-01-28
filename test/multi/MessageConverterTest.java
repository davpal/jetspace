package multi;

import entity.Player;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MessageConverterTest extends TestCase {
    private MessageBuilder builder;
    private Player player;

    @Before
    public void setUp() {
        builder = new MessageBuilder();
        player = new Player("Test", 300, 400);
    }

    @Test
    public void testJoinConversionToPacket() {
        Message join = builder.code(Message.JOIN).build();
        DatagramPacket packet = MessageConverter.toPacket(join);

        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());

        assertEquals(buffer.limit(), 1);
        assertEquals(Message.JOIN, buffer.get());
    }

    @Test
    public void testAcceptConversionToPacket() {
        Message accept = builder.code(Message.ACCEPT)
                                .pid(player.getPid())
                                .name(player.getName())
                                .position(player.getX(), player.getY())
                                .mousePosition(0, 0)
                                .build();

        DatagramPacket packet = MessageConverter.toPacket(accept);

        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());

        int code = buffer.get();
        int pid = buffer.get();
        short nameLength = buffer.getShort();
        byte[] nameArray = new byte[nameLength];
        buffer.get(nameArray);
        String name = new String(nameArray);
        int x = buffer.getInt();
        int y = buffer.getInt();

        assertEquals(24, buffer.capacity());
        assertEquals(Message.ACCEPT, code);
        assertEquals(1, pid);
        assertEquals(4, nameLength);
        assertEquals("Test", name);
        assertEquals(300, x);
        assertEquals(400, y);
    }

    @Test
    public void testPacketConversionToMessage() {
        ByteBuffer buffer = ByteBuffer.allocate(26);
        buffer.put(Message.ACCEPT);
        buffer.put((byte)1);
        buffer.putShort((short)6);
        buffer.put("Packet".getBytes());
        buffer.putInt(3);
        buffer.putInt(4);
        buffer.putInt(11);
        buffer.putInt(13);

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());

        Message accept = MessageConverter.parsePacket(packet);
    }
}
