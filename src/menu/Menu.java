package menu;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private int selected;
    
    public Menu() {
        selected = 0;
    }
    
    public void addItem(MenuItem i){
        items.add(i);
    }
    
    public Iterator<MenuItem> iterator(){
        return items.iterator();
    }
    
    public int getSelectedIndex(){
        return selected;
    }
    
    public MenuItem getSelected(){
        return items.get(selected);
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public void nextItem(){
        items.get(selected).deselect();
        ++selected;
        if (selected > items.size() - 1) selected = 0;
        items.get(selected).select();
    }
    
    public void prevItem(){
        items.get(selected).deselect();
        --selected;
        if (selected < 0) selected = items.size() - 1;
        items.get(selected).select();
    }
}
