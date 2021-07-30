package com.ss.utopia.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.repository.JdbcFlightRepository;
import com.ss.utopia.service.FlightService;

import static com.ss.utopia.util.StringUtils.newLine;

public class MainMenu extends AbstractMenu {

    private final Map<Integer, Menu> childMenus = new HashMap<>();

    public MainMenu(Console console, DataSource dataSource) {
        super(console);

        childMenus.put(1, new EmployeeMenu(console, new FlightService(new JdbcFlightRepository(dataSource))));
        childMenus.put(2, new AdministratorMenu(console));
        childMenus.put(3, new TravelerMenu(console));
    }

    public void run() throws IOException {
        final String message = "Welcome to the Utopia Airlines Management System. Which category of a user are you:" + newLine()
                + newLine()
                + "  1) Employee" + newLine()
                + "  2) Administrator" + newLine()
                + "  3) Traveler" + newLine()
                + newLine()
                + "Pick one (or 'q' to exit)? ";

        while (true) {
            String input = prompt(message);
            if ("q".equals(input)) {
                printNoColor("GOODBYE!");
                System.exit(0);
            }

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                printNoColor(input + " is not a valid option.");
                continue;
            }

            if (!childMenus.containsKey(option)) {
                printNoColor(option + " is not a valid option.");
                continue;
            }

            childMenus.get(option).run();
        }
    }

    @Override
    protected Color getColor() {
        return Color.BRIGHT_YELLOW;
    }
}
