package com.ss.utopia.menu;

import java.io.IOException;
import java.util.Map;

/**
 * Interface that represents a menu
 */
public interface Menu {

    /**
     * Run the menu
     * @throws IOException if an i/o error occurs
     */
    void run() throws IOException;

    /**
     * Get a map of menu selections
     * @return the map of menu selections
     */
    Map<Integer, MenuSelection> getMenuSelections();
}
