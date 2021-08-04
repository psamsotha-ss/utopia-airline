package com.ss.utopia.menu.traveler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

import static com.ss.utopia.util.StringUtils.newLine;

public class TravelerMenu extends AbstractMenu {

    public static final Color DEFAULT_COLOR = Color.BRIGHT_GREEN;
    private User traveler;

    @Override
    public void run() throws IOException {
        while (true) {
            String input = prompt("Enter your membership number ('q' to quit): ");
            if ("q".equals(input)) System.exit(0);

            if (isValidMember(input)) {
                break;
            } else {
                print("Invalid membership number.");
            }
        }

        printNewLine();
        super.run();
    }

    private boolean isValidMember(String input) {
        int travelerId;
        try {
            travelerId = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        UserService service = new UserService(new UserRepository());
        User user = service.getTraveler(travelerId);
        if (user != null) {
            this.traveler = user;
            return true;
        }
        return false;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to do:" + newLine()
                + newLine()
                + "  1) Book a ticket" + newLine()
                + "  2) Cancel an upcoming trip" + newLine();
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to main menu.";
    }

    @Override
    protected Color getColor() {
        return DEFAULT_COLOR;
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new FlightSelectionMenu(traveler));
        selections.put(2, new BookingCancellationMenu(traveler));
        return selections;
    }
}
