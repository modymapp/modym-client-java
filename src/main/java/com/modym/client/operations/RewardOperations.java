/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.objects.ModymPointTransaction;
import com.modym.client.objects.ModymPointTransaction.ModymPointDebitTransaction;
import com.modym.client.response.PointTransactionListResponse;
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
    public ModymPointTransaction getRewardTransaction(String transactionId) throws ModymException {
        String path = "loyalty/transactions/" + transactionId;
        return this.transport.doGet(path, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param customerId The customer id for whom we should return the loyalty transactions
     * @return
     */
    public List<ModymPointTransaction> getRewardTransactions(long memberId) throws ModymException {
        String path = "loyalty/members/" + memberId + "/transactions";
        return this.transport.doGet(path, null, null, PointTransactionListResponse.class).getResult();
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
        return this.transport.doPost("loyalty/debit/points", null, params, null, PointTransactionResponse.class)
                .getResult();
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymException
     */
    public ModymPointDebitTransaction captureDebitTransaction(long transactionId) throws ModymException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/capture";
        ModymPointTransaction transaction =
                this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
        return (ModymPointDebitTransaction) transaction;
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymException
     */
    public ModymPointDebitTransaction cancelDebitTransaction(long transactionId) throws ModymException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/cancel";
        ModymPointTransaction transaction =
                this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
        return (ModymPointDebitTransaction) transaction;
    }

}
