package menu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import menu.Menu;

public class MenuTest {
    Menu menu = new Menu();

    @Before
    public void setUp() {
        menu.addItem(new MenuItem("Single player"));
        menu.addItem(new MenuItem("Multi player"));
        menu.addItem(new MenuItem("Options"));
        menu.addItem(new MenuItem("Quit"));
    }

    @Test
    public void testEmptyMenu(){
        Menu emptyMenu = new Menu();

        assertEquals(null, emptyMenu.getSelected());
        assertEquals(-1, emptyMenu.getSelectedIndex());
    }

    @Test
    public void testFirstItemSelected(){
        assertEquals(0, menu.getSelectedIndex());
        MenuItem selected = menu.getSelected();
        assertTrue(selected.isSelected());
    }

    @Test
    public void testPrevNextItem(){
        menu.nextItem();
        assertEquals(1, menu.getSelectedIndex());
        menu.prevItem();
        assertEquals(0, menu.getSelectedIndex());

        for(int i = 0; i < 4; ++i)
            menu.nextItem();
        assertEquals(0, menu.getSelectedIndex());
        menu.prevItem();
        assertEquals(3, menu.getSelectedIndex());

        Menu oneItemMenu = new Menu();
        oneItemMenu.addItem(new MenuItem("One item"));
        oneItemMenu.nextItem();
        assertEquals(0, oneItemMenu.getSelectedIndex());
    }

    @Test
    public void testMenuItemSelectDeselect(){
        MenuItem first = menu.getSelected();
        menu.nextItem();
        MenuItem next = menu.getSelected();

        assertFalse(first.isSelected());
        assertTrue(next.isSelected());
    }

    @Test
    public void testEnterToSubmenu(){
        Menu menu = new Menu();
        Menu optionsMenu = new Menu();
        optionsMenu.addItem(new MenuItem("Fullscreen"));
        menu.addItem(optionsMenu);
        MenuItem optionsItem = menu.getSelected();
        Command options = (Command)optionsItem;
        options.execute(null);

        assertEquals(menu.getSelected().toString(), "Fullscreen");
    }
}
