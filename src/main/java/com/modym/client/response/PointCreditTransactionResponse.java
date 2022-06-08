/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymPointTransaction.ModymPointCreditTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class PointCreditTransactionResponse extends ModymResponse {

    private ModymPointCreditTransaction result;

}