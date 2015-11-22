package menu;

import org.newdawn.slick.state.StateBasedGame;

public interface Command {
    void execute(StateBasedGame g);
}