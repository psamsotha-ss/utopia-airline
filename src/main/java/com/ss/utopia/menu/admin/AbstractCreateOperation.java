package com.ss.utopia.menu.admin;

import java.io.IOException;

/**
 * Abstract menu operation class that contains helper method for getting user input.
 */
abstract class AbstractCreateOperation extends AbstractConsoleAwareOperation {

    @Override
    public boolean goBack() {
        return false;
    }

    /**
     * Get an input value from the user.
     * @param prompt the message to prompt
     * @param validator the validation method used to validate user input
     * @param errorMsg the error message if the input is not valid.
     * @return the user input
     * @throws IOException if console error occurs
     */
    protected String getInput(String prompt, InputValidator validator, String errorMsg) throws IOException {
        String input;
        while (true) {
            input = getConsole().prompt(prompt).trim();
            if (validator.validate(input)) {
                break;
            }
            getConsole().print(errorMsg != null ? errorMsg : "Invalid input.");
        }
        return input;
    }

    /**
     * Get an input value from the user.
     * @param prompt the message to prompt
     * @param validator the validation method used to validate user input
     * @return the user input
     * @throws IOException if console error occurs
     */
    protected String getInput(String prompt, InputValidator validator) throws IOException {
        return getInput(prompt, validator, null);
    }

    /**
     * Functional interface for input validation
     */
    interface InputValidator {

        /**
         * Determines whether the user input is valid
         * @param input the user input
         * @return true of the input is valid, false if it isn't
         */
        boolean validate(String input);
    }
}
