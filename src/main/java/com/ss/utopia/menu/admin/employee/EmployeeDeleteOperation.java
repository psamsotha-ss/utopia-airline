package com.ss.utopia.menu.admin.employee;

import java.io.IOException;
import java.util.List;

import com.ss.utopia.domain.User;
import com.ss.utopia.menu.admin.AbstractInputOperation;
import com.ss.utopia.repository.UserRepository;
import com.ss.utopia.service.UserService;

class EmployeeDeleteOperation extends AbstractInputOperation {

    private final User employee;
    private final List<User> employees;

    EmployeeDeleteOperation(User employee, List<User> employees) {
        this.employee = employee;
        this.employees = employees;
    }

    @Override
    public void runOperation() throws IOException {
        try {
            UserService service = new UserService(new UserRepository());
            service.deleteUser(employee);
            employees.removeIf(e -> e.getId() == employee.getId());
            getConsole().print("Employee deleted successfully.");
        } catch (Exception ex) {
            getConsole().print("Could not delete employee.");
        }
        getConsole().printNewLine();
    }

    @Override
    public boolean goBack() {
        return true;
    }
}
