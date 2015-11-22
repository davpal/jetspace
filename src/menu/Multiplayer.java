/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author dave
 */
public class Multiplayer extends MenuItem implements Command {
    public Multiplayer() {
        super("Multi player");
    }
    
    public void execute(StateBasedGame g) {
        g.enterState(3);
    }
}
