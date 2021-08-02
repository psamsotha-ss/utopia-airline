package com.ss.utopia.menu;

import java.io.IOException;
import java.util.Map;

import com.ss.utopia.console.Color;
import com.ss.utopia.console.Console;
import com.ss.utopia.console.Consoles;

import static com.ss.utopia.util.StringUtils.newLine;

/**
 * Abstract base class for menus to extends from.
 */
public abstract class AbstractMenu implements Menu {

    private static final String QUIT_PROMPT = " ('b' go back; 'q' to quit): ";
    private static final String DEFAULT_PROMPT = "Make a selection";

    protected final Console console;

    protected AbstractMenu() {
        this(Consoles.getDefaultConsole());
    }

    protected AbstractMenu(Console console) {
        this.console = console;
    }

    @Override
    public void run() throws IOException {
        while (true) {
            String initialPrompt = getInitialPrompt()
                    + newLine()
                    + getLastPromptLine() + QUIT_PROMPT;

            String input = prompt(initialPrompt).trim();
            if ("q".equals(input)) {
                printNoColor("GOODBYE!");
                System.exit(0);
            }
            if ("b".equals(input)) {
                printNoColor(getExitingMessage());
                return;
            }

            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                printNoColor(input + " is not a valid option.");
                continue;
            }

            printNewLine();

            Map<Integer, MenuSelection> selections = getMenuSelections();
            if (!selections.containsKey(option)) {
                printNoColor(option + " is not a valid option.");
                continue;
            }

            MenuSelection selection = selections.get(option);
            if (selection instanceof Menu) {
                ((Menu) selection).run();
            } else if (selection instanceof MenuOperation) {
                MenuOperation op = (MenuOperation) selection;
                op.runOperation();
                if (op.goBack()) {
                    break;
                }
            }
        }
    }

    /**
     * Get the last prompt line string.
     * @return the prompt string
     */
    protected String getLastPromptLine() {
        return DEFAULT_PROMPT;
    }

    /**
     * Get the initial prompt for the menu
     * @return the initial prompt
     */
    abstract protected String getInitialPrompt();

    /**
     * Get the color for the menu
     * @return the color
     */
    abstract protected Color getColor();

    /**
     * Get the message to present when exiting the menu
     * @return the exit message
     */
    abstract protected String getExitingMessage();

    protected String prompt(String message) throws IOException {
        return console.prompt(message, getColor());
    }

    protected void print(String message) throws IOException {
        console.print(message, getColor());
    }

    protected void printNoColor(String message) throws IOException {
        console.print(message);
    }

    protected void printNewLine() throws IOException {
        console.printNewLine();
    }
}
