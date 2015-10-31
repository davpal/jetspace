package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
    ArrayList<String> menuItems = new ArrayList<String>();
    int selected;

    StateBasedGame game;

    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        menuItems.add("Start game");
        menuItems.add("Options");
        menuItems.add("Quit");

        selected = 0;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (selected) {
                case 0:
                    game.enterState(1);
                    break;
                case 2:
                    System.exit(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ++selected;
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            --selected;

        if (selected < 0) selected = menuItems.size() - 1;
        else if (selected > menuItems.size() - 1) selected = 0;
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game1, Graphics g)
            throws SlickException {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect((gc.getWidth() - 300) / 2,
                (gc.getHeight() - 200) / 2, 300, 200);
        java.awt.Font f = new java.awt.Font("Palatino", java.awt.Font.BOLD, 30);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
        g.setFont(ttf);
        g.drawString("JetSpace v0.1",
                (gc.getWidth() - 220) / 2, (gc.getHeight() - 180) / 2);

        g.setFont(ttf);

        int position = (gc.getHeight() - 100) / 2;
        for (int i = 0; i < menuItems.size(); ++i) {
            String label = menuItems.get(i);
            if (selected == i) label = "> " + label;
            g.drawString(label, (gc.getWidth() - 250) / 2, position);
            position += 40;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_RETURN) {
            switch (selected) {
                case 0:
                    game.enterState(1);
                    break;
                case 2:
                    System.exit(0);
            }
        } else if (key == Input.KEY_DOWN)
            ++selected;
        else if (key == Input.KEY_UP)
            --selected;

        if (selected < 0) selected = menuItems.size() - 1;
        else if (selected > menuItems.size() - 1) selected = 0;
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getID() {
        return 0;
    }
}
