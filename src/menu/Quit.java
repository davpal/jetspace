package menu;

import org.newdawn.slick.state.StateBasedGame;

public class Quit extends MenuItem implements Command {
    public Quit(){
        super("Quit");
    }
    
    public void execute(StateBasedGame g) {
        System.exit(0);
    }
}
