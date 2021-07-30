package com.ss.utopia.menu;

import javax.sql.DataSource;

import com.ss.utopia.console.Console;

public class Menus {

    private Menus() {}

    public static Menu newMainMenu(Console console, DataSource dataSource) {
        return new MainMenu(console, dataSource);
    }
}
