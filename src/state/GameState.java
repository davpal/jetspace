package state;

import java.awt.Graphics;
import java.awt.event.KeyListener;

public abstract class GameState implements KeyListener {
	public abstract void paint(Graphics g);
}