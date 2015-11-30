package multi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.net.DatagramPacket;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;

public class MessageTest {

    private Message message;
    @Before
    public void initMessage() {
        message = new Message();
    }

    @Mock
    private DatagramPacket datagramPacket;

    @Test
    public void shouldParseNull() {
        assertEquals(message.parsePacket(null), null);
    }

    @Test
    public void shouldReturnNullWhenPacketIsEmpty() {
        assertEquals(message.parsePacket(datagramPacket), null);
    }

    @Test
    public void shouldReturnNewMessageWhenPacketHasContent() {
        datagramPacket = new DatagramPacket(new byte[10], 10);
        assertNotSame(message.parsePacket(datagramPacket), null);
    }

    @Test
    public void shouldReturnMessageWithSHOOTCode() {

    }
}














