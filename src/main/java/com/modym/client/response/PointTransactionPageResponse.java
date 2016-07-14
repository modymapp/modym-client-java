/************************************************************************
 * Copyright iMENA Loyalty
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
public class PointTransactionPageResponse extends ModymResponse {

    private PageResponse<ModymPointTransaction> result;

}