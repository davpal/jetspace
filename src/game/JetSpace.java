package game;

import org.newdawn.slick.*;
import state.*;

public class JetSpace extends BasicGame {
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
    public void render(GameContainer gc, Graphics g) throws SlickException {
        background.draw(0, 0);
        gameState.render(gc, g);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        background = new Image("backgrounds/level1.png");
        gameState = new Level1State(gc);
        gc.getInput().addKeyListener(gameState);
        Image cursor = new Image("player/crosshair.png");
        gc.setMouseCursor(cursor, 16, 16);
    }

    @Override
    public void update(GameContainer gc, int arg1) throws SlickException {
        gameState.update();
    }

}
