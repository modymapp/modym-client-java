/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymPointTransaction.ModymPointDebitTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class PointDebitTransactionResponse extends ModymResponse {

    private ModymPointDebitTransaction result;

}