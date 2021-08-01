package com.ss.utopia.menu.admin;

import java.io.IOException;

public class AirportUpdateOperation extends AbstractConsoleAwareOperation {

    @Override
    public void runOperation() throws IOException {
        getConsole().print("Update on Airport not supported.");
    }

    @Override
    public boolean goBack() {
        return false;
    }
}
