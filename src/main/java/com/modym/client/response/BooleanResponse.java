/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bashar
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanResponse extends ModymResponse {
    private Boolean result;
}