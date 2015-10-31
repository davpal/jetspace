package input;

import org.newdawn.slick.*;

import entity.Player;

public class PlayerInputListener extends InputAdapter {
    private Player player;
    
    public PlayerInputListener(Player player){
        this.player = player;
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
        player.setShooting(true);
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        player.setAngle(-Math.atan2((player.getCenterX()) - mx, (player.getCenterY()) - my));
    }

    public void keyPressed(int key, char arg1) {
        switch (key) {
            case Input.KEY_D: {
                player.setDx(player.getSpeed());
                break;
            }
            case Input.KEY_A: {
                player.setDx(-player.getSpeed());
                break;
            }
            case Input.KEY_W: {
                player.setDy(-player.getSpeed());
                break;
            }
            case Input.KEY_S: {
                player.setDy(player.getSpeed());
                break;
            }
            case Input.KEY_SPACE: {
                player.setShooting(true);
                break;
            }
        }
    }

    public void keyReleased(int key, char arg1) {
        switch (key) {
            case Input.KEY_D: {
                player.setDx(0);
                break;
            }
            case Input.KEY_A: {
                player.setDx(0);
                break;
            }
            case Input.KEY_W: {
                player.setDy(0);
                break;
            }
            case Input.KEY_S: {
                player.setDy(0);
                break;
            }
            case Input.KEY_SPACE: {
                player.setShooting(false);
                break;
            }
        }
    }
}