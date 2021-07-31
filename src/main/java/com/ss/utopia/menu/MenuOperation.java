package com.ss.utopia.menu;

import java.io.IOException;

/**
 * A menu selection can be either another menu, or an end operation.
 * This interface represents an end operation.
 */
public interface MenuOperation extends MenuSelection {

    /**
     * Run the operation
     * @throws IOException if an i/o error occurs during the run
     */
    void runOperation() throws IOException;

    /**
     * If the parent menu should go back or not
     * @return true if the parent menu should go back, and false if not
     */
    boolean goBack();
}
