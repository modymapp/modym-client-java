/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymProduct;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class ProductResponse extends ModymResponse {

    private ModymProduct result;

}