package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.repository.AirplaneRepository;
import com.ss.utopia.repository.AirplaneTypeRepository;
import com.ss.utopia.service.AirplaneService;

import static com.ss.utopia.util.StringUtils.newLine;

class AdminSeatsMenu extends AbstractMenu {

    private final List<AirplaneType> types;

    AdminSeatsMenu() {
        AirplaneService service = new AirplaneService(new AirplaneRepository(), new AirplaneTypeRepository());
        types = service.getAllTypes();
    }

    @Override
    protected String getInitialPrompt() {
        StringBuilder sb = new StringBuilder("Which airplane type would you like to change: ")
                .append(newLine()).append(newLine());

        for (int i = 0; i < types.size(); i++) {
            String model;
            AirplaneType type = types.get(i);
            switch (type.getId()) {
                case 1: model = "727"; break;
                case 2: model = "737"; break;
                case 3: model = "747"; break;
                case 4: model = "767"; break;
                default: model = "Unknown";
            }
            String line = "  " + (i+1) + ") " + model + " - capacity: " + type.getMaxCapacity();
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
        return "Returning to Admin menu";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        for (int i = 0; i < types.size(); i++) {
            selections.put(i + 1, new SeatsUpdateOperation(types.get(i)));
        }
        return selections;
    }
}
