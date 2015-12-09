package menu;

import org.newdawn.slick.state.StateBasedGame;

public class SinglePlayer extends MenuItem {
    public SinglePlayer(int x, int y, int width, int height){
        super("Single player", x, y, width, height);
    }
    
    public void execute(StateBasedGame g) {
        g.enterState(1);
    }
}