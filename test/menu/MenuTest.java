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
    public void testMenuItemEquals(){
        MenuItem first = new MenuItem("Resolution");
        MenuItem second = new MenuItem("Resolution");

        assertEquals(first, second);
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
        menu.execute(null);

        assertEquals("Fullscreen", menu.getSelected().toString());
    }

    @Test
    public void testNextPrevInSubmenu(){
        Menu menu = new Menu();
        Menu optionsMenu = new Menu();
        optionsMenu.addItem(new MenuItem("Fullscreen"));
        optionsMenu.addItem(new MenuItem("Resolution"));
        menu.addItem(optionsMenu);
        menu.execute(null);

        menu.nextItem();
        assertEquals("Resolution", menu.getSelected().toString());
        menu.prevItem();
        assertEquals("Fullscreen", menu.getSelected().toString());
    }

    @Test
    public void testBackFromSubmenu(){
        Menu menu = new Menu();
        Menu optionsMenu = new Menu("Options");
        optionsMenu.addItem(new MenuItem("Fullscreen"));
        optionsMenu.addItem(new MenuItem("Resolution"));
        optionsMenu.addItem(new MenuItem("Back"));
        menu.addItem(optionsMenu);
        menu.execute(null);

        menu.prevItem();
        menu.execute(null);

        assertEquals("Options", menu.getSelected().toString());
    }

    @Test
    public void testMoreSubmenus(){
        Menu menu = new Menu();
        Menu optionsMenu = new Menu("Options");
        optionsMenu.addItem(new MenuItem("Fullscreen"));
        Menu resolution = new Menu("Resolution");
        resolution.addItem(new MenuItem("1360x768"));
        resolution.addItem(new MenuItem("640x480"));
        resolution.addItem(new MenuItem("Back"));

        optionsMenu.addItem(resolution);
        optionsMenu.addItem(new MenuItem("Back"));
        menu.addItem(optionsMenu);
        menu.execute(null);
        menu.nextItem();
        menu.execute(null);
        assertEquals("1360x768", menu.getSelected().toString());
        menu.prevItem();
        menu.execute(null);
        assertEquals("Options", menu.getSelected().toString());
    }
}
