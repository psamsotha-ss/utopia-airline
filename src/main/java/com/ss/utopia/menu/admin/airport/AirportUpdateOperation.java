package com.ss.utopia.menu.admin.airport;

import java.io.IOException;

import com.ss.utopia.menu.admin.AbstractInputOperation;

class AirportUpdateOperation extends AbstractInputOperation {

    @Override
    public void runOperation() throws IOException {
        getConsole().print("Update on Airport not supported.");
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
