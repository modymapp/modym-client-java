/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public abstract class ModymResponse {
    private boolean success;
    private int elapsed;
    private String error;
}