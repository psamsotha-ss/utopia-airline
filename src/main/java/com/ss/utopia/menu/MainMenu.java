package com.ss.utopia.menu;

import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.menu.admin.AdministratorMenu;
import com.ss.utopia.menu.employee.EmployeeMenu;
import com.ss.utopia.menu.traveler.TravelerMenu;

import static com.ss.utopia.util.StringUtils.newLine;

/**
 * The main entry point menu
 */
public class MainMenu extends AbstractMenu {

    public MainMenu(Console console) {
        super(console);
    }

    @Override
    protected String getInitialPrompt() {
        return "Welcome to the Utopia Airlines Management System. Which category of a user are you:" + newLine()
                + newLine()
                + "  1) Employee" + newLine()
                + "  2) Administrator" + newLine()
                + "  3) Traveler" + newLine();
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new EmployeeMenu(console));
        selections.put(2, new AdministratorMenu(console));
        selections.put(3, new TravelerMenu(console));
        return selections;
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_YELLOW;
    }

    @Override
    protected String getExitingMessage() {
        return "GOODBYE!";
    }
}
