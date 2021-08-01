package com.ss.utopia.menu.admin;

import com.ss.utopia.domain.User;

import static com.ss.utopia.util.StringUtils.newLine;

public class EmployeeViewOperation extends AbstractViewOperation<User> {

    public EmployeeViewOperation(User employee) {
        super(employee);
    }

    @Override
    protected String formatObject(User employee) {
        return "  Give name:\t" + employee.getGivenName() + newLine()
                + "  Family name:\t" + employee.getFamilyName() + newLine()
                + "  Username:\t\t" + employee.getUsername() + newLine()
                + "  Email:\t\t" + employee.getEmail() + newLine()
                + "  Phone:\t\t" + employee.getPhone() + newLine();
    }
}
