package com.ss.utopia.menu.admin.employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.ss.utopia.console.Color;
import com.ss.utopia.db.PersistenceException;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;
import com.ss.utopia.menu.AbstractInputOperation;
import com.ss.utopia.menu.admin.AdminMenu;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

import static com.ss.utopia.util.StringUtils.newLine;

public class EmployeeUpdateMenu extends AbstractMenu {

    private final User employee;

    public EmployeeUpdateMenu(User employee) {
        this.employee = employee;
    }

    @Override
    protected String getInitialPrompt() {
        return "For " + employee.getGivenName() + ", what would you like to edit:" + newLine()
                + newLine()
                + "  1) Give name" + newLine()
                + "  2) Family name" + newLine()
                + "  3) Username" + newLine()
                + "  4) Email" + newLine()
                + "  5) Phone" + newLine();
    }

    @Override
    protected Color getColor() {
        return AdminMenu.DEFAULT_COLOR;
    }

    @Override
    protected String getExitingMessage() {
        return "Returning to Employee options.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new EmployeeFieldUpdateOperation("given_name", "given name", User::setGivenName));
        selections.put(2, new EmployeeFieldUpdateOperation("family_name", "family name", User::setFamilyName));
        selections.put(3, new EmployeeFieldUpdateOperation("username", "username", User::setUsername));
        selections.put(4, new EmployeeFieldUpdateOperation("email", "email", User::setEmail));
        selections.put(5, new EmployeeFieldUpdateOperation("phone", "phone", User::setFamilyName));
        return selections;
    }

    private class EmployeeFieldUpdateOperation extends AbstractInputOperation {

        final String field;
        final String fieldPrompt;
        final BiConsumer<User, String> callback;

        EmployeeFieldUpdateOperation(String field, String fieldPrompt, BiConsumer<User, String> callback) {
            this.field = field;
            this.fieldPrompt = fieldPrompt;
            this.callback = callback;
        }

        @Override
        public void runOperation() throws IOException {
            String input = getInput("Enter the new " + fieldPrompt + ": ", (s) -> true);
            try {
                UserService service = new UserService(new UserRepository());
                service.updateUserField(employee, field, input);
                callback.accept(employee, input);
                getConsole().print("Employee " + fieldPrompt + " updated successfully.");
            } catch (PersistenceException ex) {
                getConsole().print("Could not update employee " + fieldPrompt);
            }
            getConsole().printNewLine();
        }

        @Override
        public boolean goBack() {
            return false;
        }
    }
}
