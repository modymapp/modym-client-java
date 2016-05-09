/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymCustomer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class CustomerResponse extends ModymResponse {

    private ModymCustomer result;

}