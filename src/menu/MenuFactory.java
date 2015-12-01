package menu;

import menu.multi.Multiplayer;

public class MenuFactory {
    public static Menu createMainMenu(){
        Menu menu = new Menu();
        MenuItem singlePlayer = new SinglePlayer();
        singlePlayer.select();
        menu.addItem(singlePlayer);
        menu.addItem(new Multiplayer());
        Menu options = new Menu("Options");
        options.addItem(new Fullscreen());
        options.addItem(new MenuItem("Resolution"));
        options.addItem(new MenuItem("Sound"));
        options.addItem(new MenuItem("Back"));
        menu.addItem(options);
        menu.addItem(new Quit());

        return menu;
    }
}
