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
        if(items.isEmpty()) i.select();
        items.add(i);
    }
    
    public Iterator<MenuItem> iterator(){
        return items.iterator();
    }
    
    public int getSelectedIndex(){
        if(items.isEmpty()) return -1;
        return selected;
    }
    
    public MenuItem getSelected(){
        if(items.isEmpty()) return null;
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
