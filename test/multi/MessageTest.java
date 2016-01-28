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
}
