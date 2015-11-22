package game;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import resource.ResourceLoader;
import menu.*;

public class MenuState extends BasicGameState {
    Menu menu = new Menu();
    Image background = ResourceLoader.getImage("backgrounds/menu.png");

    StateBasedGame game;

    public MenuState(GameContainer gc, StateBasedGame g) {
        game = g;
        MenuItem singlePlayer = new SinglePlayer();
        singlePlayer.select();
        menu.addItem(singlePlayer);
        menu.addItem(new MenuItem("Multi player"));
        menu.addItem(new MenuItem("Options"));
        menu.addItem(new Quit());
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Command command = (Command)menu.getSelected();
            command.execute(game);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            menu.nextItem();
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            menu.prevItem();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game1, Graphics g)
            throws SlickException {
        Font titleFont = null;
        Font itemFont = null;
        try {
            InputStream inputStream = 
                org.newdawn.slick.util.ResourceLoader.getResourceAsStream("fonts/modern_caveman.ttf");

            java.awt.Font awtFont2 = 
                java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(32f); // set font size
            itemFont = new TrueTypeFont(awtFont2.deriveFont(24f), true);
            titleFont = new TrueTypeFont(awtFont2, true);
        } catch (Exception e) {
            e.printStackTrace();
        }	
        
        background.draw(0, 0);
        g.setColor(new Color(190, 210, 220, 255));
        g.setFont(titleFont);
        g.drawString("JetSpace v0.2",
                (gc.getWidth() - titleFont.getWidth("JetSpace v0.2")) / 2, (gc.getHeight() - 400) / 2);

        g.setFont(itemFont);

        int position = (gc.getHeight() - 100) / 2;
        Iterator it = menu.iterator();
        while(it.hasNext()){
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.setColor(new Color(190, 210, 220, 255));
            MenuItem item = ((MenuItem)it.next());
            if(item.isSelected()){
                g.setColor(new Color(255, 0, 0, 255));
            }
            g.drawRect((gc.getWidth() - 300) / 2, position - 20, 300, 50);
            g.drawString(item.toString(), (gc.getWidth() - 250) / 2, position - 10);
            position += 60;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_RETURN) {
            Command command = (Command)menu.getSelected();
            command.execute(game);
        } else if (key == Input.KEY_DOWN)
            menu.nextItem();
        else if (key == Input.KEY_UP)
            menu.prevItem();
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
