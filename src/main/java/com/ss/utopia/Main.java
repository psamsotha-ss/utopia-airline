package com.ss.utopia;

import java.io.IOException;

import com.ss.utopia.console.Consoles;
import com.ss.utopia.db.DatabaseManager;
import com.ss.utopia.menu.Menus;

public class Main {

    public static void main(String... args) throws IOException {
         DatabaseManager dbManager = DatabaseManager.getInstance();
         Menus.newMainMenu(Consoles.newReaderConsole()).run();
    }
}
