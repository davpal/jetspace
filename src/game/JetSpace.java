package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class JetSpace extends StateBasedGame {
    GameState gameState;
    
    Image background;
    
    JetSpace(){
        super("JetSpace");
    }

    public static void main(String[] args){
        try{
            AppGameContainer app = new AppGameContainer(new JetSpace());
            app.setDisplayMode(640, 480, false);
            app.setTargetFrameRate(60);
            app.start();
        }
        catch (SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new Level1State(gc));
        
        enterState(0);
    }
}
