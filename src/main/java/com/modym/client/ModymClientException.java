/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client;

import lombok.Getter;

/**
 * @author bashar
 */
public class ModymClientException extends Exception {
    private static final long serialVersionUID = 326864452189929895L;
    
    @Getter
    private final int responseCode;
    
    public ModymClientException(String message) {
        this(-1, message);
    }

    public ModymClientException(int responseCode, String message) {
        super(message);
        this.responseCode= responseCode;
    }

    public ModymClientException(String message, Object... args) {
        this(-1,String.format(message, args));
    }

    public ModymClientException(int responseCode, String message, Object... args) {
        super(String.format(message, args));
        this.responseCode= responseCode;
    }

    public ModymClientException(String message, Throwable cause, Object... args) {
        this(-1, String.format(message, args), cause);
    }

    public ModymClientException(int responseCode, String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
        this.responseCode= responseCode;
    }
}