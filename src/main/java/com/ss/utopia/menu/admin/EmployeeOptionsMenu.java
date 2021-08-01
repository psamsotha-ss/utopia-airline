package com.ss.utopia.menu.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractMenu;
import com.ss.utopia.menu.MenuSelection;

import static com.ss.utopia.util.StringUtils.newLine;

public class EmployeeOptionsMenu extends AbstractMenu {

    private final User employee;
    private final List<User> employees;

    public EmployeeOptionsMenu(User employee, List<User> employees) {
        this.employee = employee;
        this.employees = employees;
    }

    @Override
    protected String getInitialPrompt() {
        return "What would you like to do with " + employee.getGivenName() + ":" + newLine()
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
        return "Returning to employees list.";
    }

    @Override
    public Map<Integer, MenuSelection> getMenuSelections() {
        Map<Integer, MenuSelection> selections = new HashMap<>();
        selections.put(1, new EmployeeViewOperation(employee));
        return selections;
    }
}
