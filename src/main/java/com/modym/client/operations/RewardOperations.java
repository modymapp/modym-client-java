/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.math.BigDecimal;
import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.objects.ModymPointTransaction;
import com.modym.client.response.PointTransactionResponse;
import com.modym.client.utils.MapUtils;

/**
 * @author bashar
 *
 */
public class RewardOperations extends AbstractOperations {

    /**
     * 
     */
    public RewardOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * LOYALTY CALLS
     */

    /**
     * @param transactionId
     * @return
     * @throws ModymException
     */
    public ModymPointTransaction getLoyaltyTransaction(String transactionId) throws ModymException {
        String path = "loyalty/transactions/" + transactionId;
        return this.transport.doGet(path, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param customerId
     * @param points
     * @param referenceId
     * @param note
     * @return
     * @throws ModymException
     */
    public ModymPointTransaction createDebitTransaction(
            long customerId,
            BigDecimal points,
            String referenceId,
            String note) throws ModymException {
        Map<String, Object> params = MapUtils.asMap("customerId", customerId, "points", points, "referenceId",
                referenceId, "note", note, "capture", false);
        return this.transport.doPost("loyalty/debit/points", null, params, null, PointTransactionResponse.class).getResult();
    }

    public ModymPointTransaction captureDebitTransaction(long transactionId) throws ModymException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/capture";
        return this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
    }

    public ModymPointTransaction cancelDebitTransaction(long transactionId) throws ModymException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/cancel";
        return this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
    }

}
