package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class JetSpace extends StateBasedGame {
    
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
        addState(new MenuState(gc, this));
        addState(new Level1State(gc));
        addState(new GameOverState(this));
        
        enterState(0);
    }
}
