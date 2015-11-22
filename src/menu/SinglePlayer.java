package menu;

import org.newdawn.slick.state.StateBasedGame;

public class SinglePlayer extends MenuItem implements Command {
    public SinglePlayer(){
        super("Single player");
    }
    
    public void execute(StateBasedGame g) {
        g.enterState(1);
    }
}