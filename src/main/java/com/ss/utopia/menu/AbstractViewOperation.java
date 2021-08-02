package com.ss.utopia.menu;

import java.io.IOException;

public abstract class AbstractViewOperation<T> extends AbstractInputOperation {

    private final T object;

    protected AbstractViewOperation(T object) {
        this.object = object;
    }

    @Override
    public void runOperation() throws IOException {
        getConsole().print(formatObject(object));
    }

    @Override
    public boolean goBack() {
        return false;
    }

    /**
     * Format how the object should be presented to the user
     * @param object the object to be formatted
     * @return the formatted object
     */
    protected abstract String formatObject(T object);
}
