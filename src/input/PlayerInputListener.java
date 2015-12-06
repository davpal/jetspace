package input;

import entity.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import resource.ResourceFactory;

public class PlayerInputListener extends InputAdapter {
    protected Player player;
    private Audio shootEffect = ResourceFactory.getLaserSound();
    boolean enabled;
    
    public PlayerInputListener(){
        
    }
    
    public PlayerInputListener(Player player){
        this.player = player;
    }

    public boolean isAcceptingInput() {
        return enabled;
    }

    public void mouseClicked(int button, int arg1, int arg2, int arg3) {
        if(button == 0)
            shoot();
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
                shoot();
                break;
            }
        }
    }
    
    private void shoot(){
        player.setShooting(true);
        shootEffect.playAsSoundEffect(1f, 1f, false);
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

    public void disable() {
        enabled = false;
    }
    
    public void enable() {
        enabled = true;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
