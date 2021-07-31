package com.ss.utopia.menu.traveler;

import java.util.Collections;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

public class TravelerMenu extends AbstractMenu {

    public TravelerMenu(Console console) {
        super(console);
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        return Collections.emptyMap();
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_GREEN;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to main menu.";
    }

    @Override
    protected String getInitialPrompt() {
        return "Enter your membership number:";
    }

}
