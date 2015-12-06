/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.multi;

import menu.MenuItem;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author dave
 */
public class Multiplayer extends MenuItem {
    public Multiplayer(){
        super("Multiplayer");
    }

    public void execute(StateBasedGame g){
        g.enterState(3);
    }
}
