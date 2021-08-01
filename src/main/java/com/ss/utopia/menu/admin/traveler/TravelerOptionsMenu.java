package com.ss.utopia.menu.admin.traveler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.menu.admin.AdminMenu;

import static com.ss.utopia.util.StringUtils.newLine;

class TravelerOptionsMenu extends AbstractMenu {

    private final User traveler;
    private final List<User> travelers;

    TravelerOptionsMenu(User traveler, List<User> travelers) {
        this.traveler = traveler;
        this.travelers = travelers;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to do with " + traveler.getGivenName() + ":" + newLine()
                + newLine()
                + "  1) View Details" + newLine()
                + "  2) Update" + newLine()
                + "  3) Delete" + newLine();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to travelers list.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new TravelerViewOperation(traveler));
        selections.put(2, new TravelerUpdateMenu(traveler));
        selections.put(3, new TravelerDeleteOperation(traveler, travelers));
        return selections;
    }
}
