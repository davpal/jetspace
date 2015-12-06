package multi;

import org.junit.Before;
import org.junit.Test;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import static org.junit.Assert.*;

public class MessageTest {

    private Message message;

    @Before
    public void initMessage() {
        message = new Message();
    }

    @Test
    public void shouldReturnNewMessageWhenPacketHasContent() {
        byte[] content = new byte[]{123};
        DatagramPacket packet = new DatagramPacket(content, 1);
        Message messageTested = message.parsePacket(packet);
        assertNotSame(messageTested, null);
    }

    @Test
    public void shouldHaveDefinedCode() {
        byte[] code = new byte[]{11};
        DatagramPacket packet = new DatagramPacket(code, 1);
        Message messageWithCode = message.parsePacket(packet);

        assertEquals(messageWithCode.getCode(), 11);
    }

    @Test
    public void shouldHaveDefinedX() {
        byte code = 22;
        int x = 123;
        byte[] codeAndX = ByteBuffer.allocate(5).put(code).putInt(x).array();

        DatagramPacket datagramPacket = new DatagramPacket(codeAndX, 5);
        Message messageWithCodeAndX = message.parsePacket(datagramPacket);

        assertEquals(messageWithCodeAndX.getCode(), 22);
        assertEquals(messageWithCodeAndX.getX(), 123);
    }

    @Test
    public void shouldHaveDefinedCodeAndXAndYAndAngle() {
        int x = 155;
        int y = 355;
        byte code = 33;
        double angle = 12345.1234;

        byte[] codeAndXAndYAndAngle = ByteBuffer.allocate(17)
                .put(code)
                .putInt(x)
                .putInt(y)
                .putDouble(angle)
                .array();
        DatagramPacket datagramPacket = new DatagramPacket(codeAndXAndYAndAngle, 17);
        Message messageWithCodeAndXAndYAndAngle = message.parsePacket(datagramPacket);

        assertEquals(messageWithCodeAndXAndYAndAngle.getY(), 355);
        assertEquals(messageWithCodeAndXAndYAndAngle.getAngle(), 12345.1234, 0.0);
    }

    @Test
    public void shouldReturnPacketWithSomeContent() {
        byte code = 55;
        message = new Message(code, 0, 0, 0.0);
        assertNotNull(message.toPacket());
    }

    @Test
    public  void shouldParseWholeMessageToPacket() {
        byte code = 66;
        message = new Message(code, 15,234,123.123);

        DatagramPacket datagramPacket = message.toPacket();
        Message messageTested = Message.parsePacket(datagramPacket);

        assertEquals(message.getCode(), messageTested.getCode());
        assertEquals(message.getAngle(), messageTested.getAngle(), 0);
        assertEquals(message.getX(), messageTested.getX());
        assertEquals(message.getY(), messageTested.getY());
    }

}














