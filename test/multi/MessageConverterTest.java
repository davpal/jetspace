package multi;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MessageConverterTest extends TestCase {
    MessageBuilder builder;

    @Before
    public void setUp() {
        builder = new MessageBuilder();
    }

    @Test
    public void testJoinConvertionToPacket() {
        Message join = builder.code(Message.JOIN).build();
        DatagramPacket packet = MessageConverter.toPacket(join);

        ByteBuffer buffer = ByteBuffer.wrap(packet.getData());

        assertEquals(buffer.limit(), 1);
        assertEquals(Message.JOIN, buffer.get());
    }
}
