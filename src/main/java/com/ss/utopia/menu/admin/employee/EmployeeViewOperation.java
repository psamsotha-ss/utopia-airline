package com.ss.utopia.menu.admin.employee;

import com.ss.utopia.domain.User;
import com.ss.utopia.menu.AbstractViewOperation;

import static com.ss.utopia.util.StringUtils.newLine;

class EmployeeViewOperation extends AbstractViewOperation<User> {

    EmployeeViewOperation(User employee) {
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
