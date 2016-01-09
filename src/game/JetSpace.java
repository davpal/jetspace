package game;

import menu.multi.Multiplayer;
import multi.MultiplayerSetupState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class JetSpace extends StateBasedGame {
    public static final String TITLE = "JetSpace v0.2.1";
    
    JetSpace(){
        super(TITLE);
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
        addState(new MultiplayerSetupState(gc));
        addState(new Level1State(gc));
        addState(new GameOverState(this));
        addState(new WinnerState(this));
        
        enterState(0);
    }
}
