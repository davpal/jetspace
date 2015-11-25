package input;

import entity.ControlledPlayer;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;
import resource.ResourceFactory;

public class PlayerInputListener extends InputAdapter {
    private ControlledPlayer controlledPlayer;
    private Audio shootEffect = ResourceFactory.getLaserSound();
    
    public PlayerInputListener(ControlledPlayer controlledPlayer){
        this.controlledPlayer = controlledPlayer;
    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
        shoot();
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
                shoot();
                break;
            }
        }
    }
    
    private void shoot(){
        controlledPlayer.setShooting(true);
        shootEffect.playAsSoundEffect(1f, 1f, false);
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
