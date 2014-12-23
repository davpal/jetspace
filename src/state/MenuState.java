package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MenuState extends GameState implements KeyListener {
	ArrayList<String> menuItems = new ArrayList<String>();
	int selected;

	public MenuState(){
		menuItems.add("Start game");
		menuItems.add("Options");
		menuItems.add("Quit");

		selected = 0;
	}

	public void paint(Graphics g){
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect((315 - 300) / 2,
				(600 - 200) / 2, 300, 200);
		g.setFont(new Font("Palatino", Font.BOLD, 30));
		g.drawString("JetSpace v0.1", 50, 230);

		g.setFont(new Font("Tahoma", Font.PLAIN, 20));

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
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			++selected;
		else if(e.getKeyCode() == KeyEvent.VK_UP)
			--selected;

		if(selected < 0) selected = menuItems.size() - 1;
		else if(selected > menuItems.size() - 1) selected = 0;
	}

	public void keyTyped(KeyEvent e){

	}
}
