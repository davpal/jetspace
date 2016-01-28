package multi;

import entity.Player;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {
    private MessageBuilder builder;
    private Player player;

    @Before
    public void setUp() {
        player = new Player("Test", 300, 400);
        builder = new MessageBuilder();
    }

    @Test
    public void testBuildJoinMessage() {
        Message join = builder.code(Message.JOIN).build();

        assertEquals(Message.JOIN, join.getCode());
        assertEquals(1, join.getSize());
    }

    @Test
    public void testBuildAcceptMessage() {
        Message accept = builder.code(Message.ACCEPT)
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
        Message sync = builder.code(Message.SYNC)
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
}
