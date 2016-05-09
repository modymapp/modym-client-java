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
public class StringResponse extends ModymResponse {

    private String result;

}