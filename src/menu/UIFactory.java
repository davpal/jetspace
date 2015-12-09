package menu;

import menu.multi.Multiplayer;

public class UIFactory {
    public static Menu createMainMenu(int gcWidth, int gcHeight){
        int x = (gcWidth - 300) / 2;
        int y = (gcHeight - 100) / 2;
        int width = 300;
        int height = 50;

        int dy = 60;

        Menu mainMenu = new Menu("Main menu");
        mainMenu.addItem(new SinglePlayer(x, y, width, height));
        mainMenu.addItem(new Multiplayer(x, y + dy, width, height));

        Menu options = new Menu("Options", x, y + 2 * dy, width, height);
        options.addItem(new Fullscreen(x, y, width, height));
        options.addItem(new MenuItem("Resolution", x, y + dy, width, height));
        options.addItem(new MenuItem("Sound", x, y + 2 * dy, width, height));
        options.addItem(new MenuItem("Back", x, y + 3 * dy, width, height));

        mainMenu.addItem(options);
        mainMenu.addItem(new Quit(x, y + 3 * dy, width, height));

        return mainMenu;
    }
}
