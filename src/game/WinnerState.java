package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import resource.ResourceLoader;

public class WinnerState extends BasicGameState {
    private StateBasedGame game;
    org.newdawn.slick.Font font = ResourceLoader.getFont("fonts/modern_caveman.ttf", 50f);
    private int textHeight;
    private int textWidth;

    WinnerState(StateBasedGame g) {
        game = g;
        textWidth = font.getWidth("Winner");
        textHeight = font.getHeight("Winner");
    }

    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        // TODO Auto-generated method stub
    }

    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(Color.green);
        g.setFont(font);
        g.drawString("Winner", (gc.getWidth() - textWidth) / 2, (gc.getHeight() - textHeight) / 2);
    }

    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
        // TODO Auto-generated method stub
    }

    @Override
    public int getID() {
        return 4;
    }
}
