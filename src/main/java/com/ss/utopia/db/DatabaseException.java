package com.ss.utopia.db;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
