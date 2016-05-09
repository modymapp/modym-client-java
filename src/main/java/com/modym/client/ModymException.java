/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client;

/**
 * @author bashar
 */
public class ModymException extends Exception {
    private static final long serialVersionUID = 326864452189929895L;

    public ModymException(String message) {
        super(message);
    }

    public ModymException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ModymException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }
}