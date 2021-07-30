package com.ss.utopia;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ss.utopia.console.Consoles;
import com.ss.utopia.db.DatabaseManager;
import com.ss.utopia.menu.Menus;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) throws SQLException, IOException {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        DataSource dataSource = dbManager.getDataSource();
        Menus.newMainMenu(Consoles.newReaderConsole(), dataSource).run();
    }
}
