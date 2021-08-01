package com.ss.utopia.db;

public class PersistenceException extends RuntimeException {

    public PersistenceException(String msg) {
        super(msg);
    }

    public PersistenceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
