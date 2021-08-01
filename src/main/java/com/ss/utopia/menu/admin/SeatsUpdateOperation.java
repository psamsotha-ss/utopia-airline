package com.ss.utopia.menu.admin;

import java.io.IOException;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.repository.AirplaneRepository;
import com.ss.utopia.repository.AirplaneTypeRepository;
import com.ss.utopia.service.AirplaneService;

class SeatsUpdateOperation extends AbstractInputOperation {

    private final AirplaneType type;

    SeatsUpdateOperation(AirplaneType type) {
        this.type = type;
    }

    @Override
    public void runOperation() throws IOException {
        String capacityInput = getInput("What is the new capacity: ", (input) -> {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }, "Must be an integer.");
        int newCapacity = Integer.parseInt(capacityInput);

        try {
            AirplaneService service = new AirplaneService(new AirplaneRepository(), new AirplaneTypeRepository());
            service.updatePlaneTypeCapacity(type, newCapacity);
            getConsole().print("Capacity updated successfully.");
        } catch (PersistenceException ex) {
            getConsole().print("Capacity could not be updated.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
