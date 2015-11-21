package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import resource.ResourceLoader;

public class MenuState extends BasicGameState {
    ArrayList<String> menuItems = new ArrayList<String>();
    int selected;
    Image background = ResourceLoader.getImage("backgrounds/menu.png");

    StateBasedGame game;

    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        menuItems.add("Single player");
        menuItems.add("Multi player");
        menuItems.add("Options");
        menuItems.add("Quit");

        selected = 0;
    }
    
    public int getSelected(){
        return selected;
    }
    
    public void nextItem(){
        ++selected;
        if (selected > menuItems.size() - 1) selected = 0;
    }
    
    public void prevItem(){
        --selected;
        if (selected < 0) selected = menuItems.size() - 1;
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
        background.draw(0, 0);
        g.setColor(new Color(190, 210, 220, 255));
        java.awt.Font f = new java.awt.Font("Candara", java.awt.Font.BOLD, 30);
        TrueTypeFont ttf = new TrueTypeFont(f, true);
        g.setFont(ttf);
        g.drawString("JetSpace v0.2",
                (gc.getWidth() - 220) / 2, (gc.getHeight() - 400) / 2);

        g.setFont(ttf);

        int position = (gc.getHeight() - 100) / 2;
        for (int i = 0; i < menuItems.size(); ++i) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.setColor(new Color(190, 210, 220, 255));
            String label = menuItems.get(i);
            if (selected == i){
                g.setColor(new Color(255, 0, 0, 255));
            }
            g.drawRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.drawString(label, (gc.getWidth() - 250) / 2, position - 10);
            position += 60;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_RETURN) {
            switch (selected) {
                case 0:
                    game.enterState(1);
                    break;
                case 3:
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
