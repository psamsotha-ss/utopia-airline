package com.ss.utopia.menu.admin.traveler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.menu.admin.AdminMenu;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

import static com.ss.utopia.util.StringUtils.newLine;
import static java.util.Comparator.comparing;

public class AdminTravelerMenu extends AbstractMenu {

    private final List<User> travelers;

    public AdminTravelerMenu() {
        UserService service = new UserService(new UserRepository());
        travelers = service.getAllTravelers();
        travelers.sort(comparing(User::getFamilyName));
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Which traveler would you like to manage:").append(newLine())
                .append(newLine());

        sb.append("  1) Create new traveler").append(newLine());

        for (int i = 0; i < travelers.size(); i++) {
            User traveler = travelers.get(i);
            String line = "  " + (i+2) + ") " + traveler.getFamilyName()
                    + ", " + traveler.getGivenName() + " (" + traveler.getUsername() + ")";
            sb.append(line).append(newLine());
        }
        return sb.toString();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Admin menu.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new TravelerCreateOperation(travelers));

        for (int i = 0; i < travelers.size(); i++) {
            selections.put(i + 2, new TravelerOptionsMenu(travelers.get(i), travelers));
        }
        return selections;
    }
}
