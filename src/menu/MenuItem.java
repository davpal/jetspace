package menu;

import org.newdawn.slick.state.StateBasedGame;

public class MenuItem {
    String title;
    boolean selected;
    
    public MenuItem(){

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
}
