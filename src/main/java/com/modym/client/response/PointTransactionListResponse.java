/************************************************************************
 * Copyright iMENA Loyalty
 */
package com.modym.client.response;

import java.util.List;

import com.modym.client.objects.ModymPointTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class PointTransactionListResponse extends ModymResponse {

    private List<ModymPointTransaction> result;

}