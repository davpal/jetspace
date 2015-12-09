package menu;

import org.newdawn.slick.state.StateBasedGame;

public class Quit extends MenuItem {
    public Quit(int x, int y, int width, int height){
        super("Quit", x, y, width, height);
    }
    
    public void execute(StateBasedGame g) {
        System.exit(0);
    }
}
