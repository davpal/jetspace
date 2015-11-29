package menu;

import java.util.ArrayList;
import java.util.Iterator;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends MenuItem {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private Menu parent;
    private int selected;
    private boolean inside;
    
    public Menu() {
        parent = null;
        selected = 0;
    }
    
    public Menu(String n) {
        super(n);
    }

    private void setParent(Menu m){
        parent = m;
    }

    private Menu getParent(){
        return parent;
    }

    public void addItem(MenuItem i){
        if(i instanceof Menu){
            Menu submenu = (Menu)i;
            submenu.setParent(this);
        }
        if(items.isEmpty()) i.select();
        items.add(i);
    }
    
    public Iterator<MenuItem> iterator(){
        if(inside){
            Menu submenu = (Menu)items.get(selected);
            return submenu.iterator();
        }
        return items.iterator();
    }
    
    public int getSelectedIndex(){
        if(items.isEmpty()) return -1;
        return selected;
    }
    
    public MenuItem getSelected(){
        if(items.isEmpty()) return null;
        if(inside){
            Menu submenu = (Menu)items.get(selected);
            return submenu.getSelected();
        }
        return items.get(selected);
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public void nextItem(){
        if(inside){
            Menu submenu = (Menu)items.get(selected);
            submenu.nextItem();
        } else {
            items.get(selected).deselect();
            ++selected;
            if (selected > items.size() - 1) selected = 0;
            items.get(selected).select();
        }
    }
    
    public void prevItem(){
        if(inside){
            Menu submenu = (Menu)items.get(selected);
            submenu.prevItem();
        } else {
            items.get(selected).deselect();
            --selected;
            if (selected < 0) selected = items.size() - 1;
            items.get(selected).select();
        }
    }

    private void back(){
        inside = false;
    }

    public void execute(StateBasedGame g){
        if(getSelected().equals(new MenuItem("Back"))){
            Menu submenu = (Menu)items.get(selected);
            submenu.getParent().back();
        } else if(inside){
            Menu submenu = (Menu)items.get(selected);
            submenu.execute(g);
        } else if(items.get(selected) instanceof Menu){
            inside = true;
        } else {
            getSelected().execute(g);
        }
    }
}
