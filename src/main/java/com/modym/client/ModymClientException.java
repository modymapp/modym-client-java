/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client;

/**
 * @author bashar
 */
public class ModymClientException extends Exception {
    private static final long serialVersionUID = 326864452189929895L;

    public ModymClientException(String message) {
        super(message);
    }

    public ModymClientException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ModymClientException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }
}