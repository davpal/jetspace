package menu;

import org.newdawn.slick.state.StateBasedGame;
import rendering.Renderer;

public class MenuItem {
    int x, y, width, height;
    String title;
    boolean selected;
    
    public MenuItem(String title, int x, int y, int width, int height){
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MenuItem(String t){
        title = t;
    }
    
    public String toString(){
        return title;
    }
    
    public boolean isSelected(){
        return selected;
    }
    
    public void select(){
        selected = true;
    }
    
    public void deselect(){
        selected = false;
    }

    public boolean equals(Object o){
        MenuItem i = (MenuItem)o;
        return i.title.equals(title);
    }

    public void execute(StateBasedGame g){

    }

    public void render(Renderer r){
        r.renderMenuItem(this);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
