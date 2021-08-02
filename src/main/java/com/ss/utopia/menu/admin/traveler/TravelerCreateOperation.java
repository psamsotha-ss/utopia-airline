package com.ss.utopia.menu.admin.traveler;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

import static java.util.Comparator.comparing;

class TravelerCreateOperation extends AbstractInputOperation {

    private final List<User> travelers;

    TravelerCreateOperation(List<User> travelers) {
        this.travelers = travelers;
    }

    @Override
    public void runOperation() throws IOException {
        String givenName = getInput("Enter given name: ", (input) -> input.length() < 256);
        String familyName = getInput("Enter family name: ", (input) -> input.length() < 256);
        String username = getInput("Enter username: ", (input) -> input.length() < 46);
        String password = getInput("Enter password: ", (input) -> input.length() < 265);
        String email = getInput("Enter email: ", (input) -> input.length() < 265);

        UserService service = new UserService(new UserRepository());
        String phone = getInput("Enter phone (111-111-1111): ", new PhoneValidator(service),
                "Phone number already exists or is not in correct format.");
        try {
            User user = new User();
            user.setGivenName(givenName);
            user.setFamilyName(familyName);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user = service.createNewTraveler(user);
            travelers.add(user);
            travelers.sort(comparing(User::getFamilyName));
            getConsole().printNewLine();
            getConsole().print("Employee created successfully.");
        } catch (PersistenceException ex) {
            getConsole().printNewLine();
            getConsole().print("Employee could not be created.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return false;
    }

    private static class PhoneValidator implements InputValidator {

        final UserService service;

        PhoneValidator(UserService service) {
            this.service = service;
        }

        @Override
        public boolean validate(String input) {
            if (!input.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
                return false;
            }
            return !service.checkPhoneNumberExists(input);
        }
    }
}
