package com.ss.utopia.menu;

import com.ss.utopia.console.Console;

public class Menus {

    private Menus() {}

    public static Menu newMainMenu(Console console) {
        return new MainMenu(console);
    }
}
