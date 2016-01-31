package multi;

import entity.Player;
import java.net.Inet4Address;
import java.net.InetAddress;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MessageBuilderTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player("Test", 300, 400);
    }

    @Test
    public void testBuildJoinMessage() {
        Message join = new Message.Builder()
                                  .code(Message.JOIN)
                                  .pid((byte)1)
                                  .build();

        assertEquals(Message.JOIN, join.getCode());
        assertEquals(2, join.getSize());
        assertEquals(1, join.getPid());
    }

    @Test
    public void testBuildAcceptMessage() {
        Message accept = new Message.Builder()
                                    .code(Message.ACCEPT)
                                    .pid(player.getPid())
                                    .name(player.getName())
                                    .position(player.getX(), player.getY())
                                    .mousePosition(0, 0)
                                    .build();

        assertEquals(Message.ACCEPT, accept.getCode());
        assertEquals(22, accept.getSize());
        assertEquals(300, accept.getX());
        assertEquals(400, accept.getY());
    }

    @Test
    public void testBuildSyncMessage() {
        Message sync = new Message.Builder()
                                  .code(Message.SYNC)
                                  .pid(player.getPid())
                                  .name(player.getName())
                                  .position(player.getX(), player.getY())
                                  .mousePosition(0, 0)
                                  .build();

        assertEquals(Message.SYNC, sync.getCode());
        assertEquals(22, sync.getSize());
        assertEquals(300, sync.getX());
        assertEquals(400, sync.getY());
    }

    @Test
    public void testBuildMoveMessage() {
        player.setDx(3);
        player.setDy(4);

        Message move = new Message.Builder()
                                  .code(Message.MOVE)
                                  .pid(player.getPid())
                                  .shifts(player.getDx(), player.getDy())
                                  .mousePosition(100, 200)
                                  .build();

        assertEquals(Message.MOVE, move.getCode());
        assertEquals(18, move.getSize());
        assertEquals(3, move.getDx());
        assertEquals(4, move.getDy());
        assertEquals(100, move.getMouseX());
        assertEquals(200, move.getMouseY());
    }

    @Test
    public void testBuildMessageWithSourceAddress() {
        InetAddress address = Inet4Address.getLoopbackAddress();

        Message remoteMessage = new Message.Builder().code(Message.JOIN)
                                           .pid((byte)1)
                                           .source(address)
                                           .build();

        assertEquals(Message.JOIN, remoteMessage.getCode());
        assertEquals(address, remoteMessage.getSource());
        assertEquals(2, remoteMessage.getSize());
    }
}
