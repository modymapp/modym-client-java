/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymPointTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class PointTransactionResponse extends ModymResponse {

    private ModymPointTransaction result;

}