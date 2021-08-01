package com.ss.utopia.menu.admin.traveler;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.domain.User;
import com.ss.utopia.menu.admin.AbstractInputOperation;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

class TravelerDeleteOperation extends AbstractInputOperation {

    private final User traveler;
    private final List<User> travelers;

    TravelerDeleteOperation(User traveler, List<User> travelers) {
        this.traveler = traveler;
        this.travelers = travelers;
    }

    @Override
    public void runOperation() throws IOException {
        try {
            UserService service = new UserService(new UserRepository());
            service.deleteUser(traveler);
            travelers.removeIf(t -> t.getId().equals(traveler.getId()));
            getConsole().print("Traveler deleted successfully.");
        } catch (Exception ex) {
            getConsole().print("Could not delete traveler.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return true;
    }
}
