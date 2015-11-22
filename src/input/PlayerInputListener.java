package input;

import entity.ControlledPlayer;
import org.newdawn.slick.Input;

public class PlayerInputListener extends InputAdapter {
    private ControlledPlayer controlledPlayer;
    
    public PlayerInputListener(ControlledPlayer controlledPlayer){
        this.controlledPlayer = controlledPlayer;
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
        controlledPlayer.setShooting(true);
    }

    public void mouseMoved(int arg0, int arg1, int mx, int my) {
        controlledPlayer.setAngle(-Math.atan2((controlledPlayer.getCenterX()) - mx, (controlledPlayer.getCenterY()) - my));
    }

    public void keyPressed(int key, char arg1) {
        switch (key) {
            case Input.KEY_D: {
                controlledPlayer.setDx(controlledPlayer.getSpeed());
                break;
            }
            case Input.KEY_A: {
                controlledPlayer.setDx(-controlledPlayer.getSpeed());
                break;
            }
            case Input.KEY_W: {
                controlledPlayer.setDy(-controlledPlayer.getSpeed());
                break;
            }
            case Input.KEY_S: {
                controlledPlayer.setDy(controlledPlayer.getSpeed());
                break;
            }
            case Input.KEY_SPACE: {
                controlledPlayer.setShooting(true);
                break;
            }
        }
    }

    public void keyReleased(int key, char arg1) {
        switch (key) {
            case Input.KEY_D: {
                controlledPlayer.setDx(0);
                break;
            }
            case Input.KEY_A: {
                controlledPlayer.setDx(0);
                break;
            }
            case Input.KEY_W: {
                controlledPlayer.setDy(0);
                break;
            }
            case Input.KEY_S: {
                controlledPlayer.setDy(0);
                break;
            }
            case Input.KEY_SPACE: {
                controlledPlayer.setShooting(false);
                break;
            }
        }
    }
}