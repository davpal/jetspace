package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import app.Game;

public class GameOverState extends GameState {

    GameOverState(Game g) {
        super(g);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        game.setState(new MenuState(game));
        game.removeKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect((315 - 300) / 2,
                (600 - 200) / 2, 300, 50);
        g.setFont(new Font("Palatino", Font.BOLD, 30));
        g.setColor(new Color(255,0,0));
        g.drawString("GAME OVER", 50, 235);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
