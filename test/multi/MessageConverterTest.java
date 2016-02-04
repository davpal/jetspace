package multi;

import entity.Player;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MessageConverterTest extends TestCase {
    private MessageBuilder builder;
    private Player player;

    @Before
    @Override
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
    public void testMoveConversionToPacket() {
        Message move = builder.code(Message.MOVE)
                                .pid(player.getPid())
                                .shifts(5, -5)
                                .mousePosition(0, 0)
                                .build();

        DatagramPacket packet = MessageConverter.toPacket(move);

        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());

        int code = buffer.get();
        int pid = buffer.get();
        int dx = buffer.getInt();
        int dy = buffer.getInt();

        assertEquals(18, buffer.capacity());
        assertEquals(Message.MOVE, code);
        assertEquals(1, pid);
        assertEquals(5, dx);
        assertEquals(-5, dy);
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
        packet.setAddress(InetAddress.getLoopbackAddress());

        Message accept = MessageConverter.parsePacket(packet);

        assertEquals(Message.ACCEPT, accept.getCode());
        assertEquals(1, accept.getPid());
        assertEquals("Packet", accept.getName());
        assertEquals(3, accept.getX());
        assertEquals(4, accept.getY());
        assertEquals(11, accept.getMouseX());
        assertEquals(13, accept.getMouseY());
        assertEquals(InetAddress.getLoopbackAddress(), accept.getSource());
    }

    @Test
    public void testRawDataConversionToMessage() {
        byte[] raw = new byte[] {
            0x16, 0x01,
            0x00, 0x04,
            0x64, 0x61, 0x76, 0x65,
            0x00, 0x00, 0x01, 0x2C, 0x00, 0x00, 0x00, (byte)0xC8,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        DatagramPacket packet = new DatagramPacket(raw, raw.length);

        Message result = MessageConverter.parsePacket(packet);

        assertEquals(300, result.getX());
        assertEquals(200, result.getY());
    }

    @Test
    public void testPacketConversionToMoveMessage() {
        ByteBuffer buffer = ByteBuffer.allocate(26)
                                      .put(Message.MOVE)
                                      .put((byte)1)
                                      .putInt(3)
                                      .putInt(4)
                                      .putInt(11)
                                      .putInt(13);

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());

        Message move = MessageConverter.parsePacket(packet);

        assertEquals(3, move.getDx());
        assertEquals(4, move.getDy());
        assertEquals(11, move.getMouseX());
        assertEquals(13, move.getMouseY());
    }

    @Test
    public void testPacketConversionToStopMessage() {
        ByteBuffer buffer = ByteBuffer.allocate(18);
        buffer.put(Message.STOP);
        buffer.put((byte)1);
        buffer.putInt(3);
        buffer.putInt(4);
        buffer.putInt(11);
        buffer.putInt(13);

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        packet.setAddress(InetAddress.getLoopbackAddress());

        Message accept = MessageConverter.parsePacket(packet);

        assertEquals(Message.STOP, accept.getCode());
        assertEquals(1, accept.getPid());
        assertEquals(3, accept.getX());
        assertEquals(4, accept.getY());
        assertEquals(0, accept.getDx());
        assertEquals(0, accept.getDy());
        assertEquals(11, accept.getMouseX());
        assertEquals(13, accept.getMouseY());
        assertEquals(InetAddress.getLoopbackAddress(), accept.getSource());
    }

    @Test
    public void testAlotConversionToPacket() {
        for(int i = 0; i < 1000; ++i) {
            Message move = builder.code(Message.MOVE)
                                  .pid(player.getPid())
                                  .shifts(5, -5)
                                  .mousePosition(4, 7)
                                  .build();

            DatagramPacket packet = MessageConverter.toPacket(move);
        }
    }
}
