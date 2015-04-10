package state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.newdawn.slick.*;

public class MenuState extends GameState implements KeyListener {
    ArrayList<String> menuItems = new ArrayList<String>();  
    int selected;
    
    public MenuState(GameContainer gc) {
        super(gc);
        
        menuItems.add("Start game");
        menuItems.add("Options");
        menuItems.add("Quit");
        
        selected = 0;
    }

    public void paint(java.awt.Graphics g){
        g.setColor(new java.awt.Color(255, 255, 255, 100));
        g.fillRect((315 - 300) / 2,
                (600 - 200) / 2, 300, 200);
        g.setFont(new java.awt.Font("Palatino", java.awt.Font.BOLD, 30));
        g.drawString("JetSpace v0.1", 50, 230);
        
        g.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
        
        int position = 260;
        for(int i = 0; i < menuItems.size(); ++i){
            String label = menuItems.get(i);
            if(selected == i) label = "> " + label;
            g.drawString(label, 20, position);
            position += 40;
        }
    }
    
    public void keyPressed(KeyEvent e){
        
    }
    
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            switch(selected){
                case 0:
                    gc.getInput().removeAllKeyListeners();
                    //game.setState(new Level1State(game));
                    break;
                case 2:
                    System.exit(0);
            } 
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            ++selected;
        else if(e.getKeyCode() == KeyEvent.VK_UP)
            --selected;
        
        if(selected < 0) selected = menuItems.size() - 1;
        else if(selected > menuItems.size() - 1) selected = 0;
    }
    
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
    
    public void render(GameContainer gc, Graphics g){
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
        for(int i = 0; i < menuItems.size(); ++i){
            String label = menuItems.get(i);
            if(selected == i) label = "> " + label;
            g.drawString(label, (gc.getWidth() - 250) / 2, position);
            position += 40;
        }
    }

    @Override
    public void inputEnded() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputStarted() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void setInput(Input arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == Input.KEY_RETURN){
            switch(selected){
                case 0:
                    gc.getInput().removeAllKeyListeners();
                    break;
                case 2:
                    System.exit(0);
            } 
        }
        else if(key == Input.KEY_DOWN)
            ++selected;
        else if(key == Input.KEY_UP)
            --selected;
        
        if(selected < 0) selected = menuItems.size() - 1;
        else if(selected > menuItems.size() - 1) selected = 0;
    }

    @Override
    public void keyReleased(int key, char c) {
        
    }
}
