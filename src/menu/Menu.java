package menu;

import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Iterator;

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
            return getSubmenu().iterator();
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
            return getSubmenu().getSelected();
        }
        return items.get(selected);
    }

    public void setSelected(int selected) {
        if(inside){
            getSubmenu().setSelected(selected);
        } else {
            getSelected().deselect();
            this.selected = selected;
            items.get(selected).select();
        }
    }

    public void nextItem(){
        if(inside){
            getSubmenu().nextItem();
        } else {
            items.get(selected).deselect();
            ++selected;
            if (selected > items.size() - 1) selected = 0;
            items.get(selected).select();
        }
    }
    
    public void prevItem(){
        if(inside){
            getSubmenu().prevItem();
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
            getSubmenu().execute(g);
        } else if(items.get(selected) instanceof Menu){
            inside = true;
        } else {
            getSelected().execute(g);
        }
    }

    private Menu getSubmenu(){
        if(inside){
            Menu submenu = (Menu)items.get(selected);
            return submenu;
        }
        return this;
    }
}
