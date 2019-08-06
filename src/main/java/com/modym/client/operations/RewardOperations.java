/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymPointTransaction;
import com.modym.client.objects.ModymPointTransaction.ModymPointDebitTransaction;
import com.modym.client.objects.ModymPurchaseRewardSummary;
import com.modym.client.response.MapListResponse;
import com.modym.client.response.PageResponse;
import com.modym.client.response.PointDebitTransactionResponse;
import com.modym.client.response.PointTransactionListResponse;
import com.modym.client.response.PointTransactionPageResponse;
import com.modym.client.response.PointTransactionResponse;
import com.modym.client.response.PurchaseLoyaltyListResponse;
import com.modym.client.utils.ModymMapUtils;

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
     * @throws ModymClientException
     */
    public ModymPointTransaction getRewardTransaction(String transactionId) throws ModymClientException {
        String path = "loyalty/transactions/" + transactionId;
        return this.transport.doGet(path, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param filters
     * @return
     * @throws ModymClientException
     */
    public List<Map<String, Object>> getRewardCategoryBreakdown(Map<String, Object> filters)
            throws ModymClientException {
        String path = "loyalty/transaction-breakdown";
        return this.transport.doGet(path, filters, null, MapListResponse.class).getResult();
    }

    /**
     * @param filters
     * @return
     * @throws ModymClientException
     */
    public List<Map<String, Object>> getMonthlyReward(Map<String, Object> filters)
            throws ModymClientException {
        String path = "loyalty/transaction-monthly";
        return this.transport.doGet(path, filters, null, MapListResponse.class).getResult();
    }

    /**
     * @param filters
     * @return
     */
    public PageResponse<ModymPointTransaction> getRewardTransactions(Map<String, Object> filters)
            throws ModymClientException {
        String path = "loyalty/transactions/";
        return this.transport.doGet(path, filters, null, PointTransactionPageResponse.class).getResult();
    }

    /**
     * @param memberId The customer id for whom we should return the loyalty transactions
     * @return
     */
    public List<ModymPointTransaction> getRewardTransactions(long memberId) throws ModymClientException {
        String path = "loyalty/members/" + memberId + "/transactions";
        return this.transport.doGet(path, null, null, PointTransactionListResponse.class).getResult();
    }

    /**
     * @param customerId
     * @param points
     * @param referenceId
     * @param note
     * @return
     * @throws ModymClientException
     */
    public ModymPointDebitTransaction createDebitTransaction(
            long customerId,
            BigDecimal points,
            String referenceId,
            String note) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "points", points, "referenceId",
                referenceId, "note", note, "capture", false);
        String path = "loyalty/debit/points";
        return this.transport.doPost(path, null, params, null, PointDebitTransactionResponse.class).getResult();
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymClientException
     */
    public ModymPointDebitTransaction captureDebitTransaction(long transactionId) throws ModymClientException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/capture";
        return this.transport.doPut(path, null, null, null, PointDebitTransactionResponse.class).getResult();
    }

    /**
     * @param transactionId
     * @param verificationCode
     * @return
     * @throws ModymClientException
     */
    public ModymPointDebitTransaction captureDebitTransaction(long transactionId, String verificationCode) throws ModymClientException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/capture";
        Map<String, Object> params = ModymMapUtils.asMap("authorizationCode", verificationCode);
        
        return this.transport.doPut(path, null, params, null, PointDebitTransactionResponse.class).getResult();
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymClientException
     */
    public ModymPointDebitTransaction cancelDebitTransaction(long transactionId) throws ModymClientException {
        String path = "loyalty/debit/" + Long.toString(transactionId) + "/cancel";
        return this.transport.doPut(path, null, null, null, PointDebitTransactionResponse.class).getResult();
    }

    /**
     * @param customerId
     * @param points
     * @param expiryMonths
     * @param note
     * @return
     * @throws ModymClientException
     */
    public ModymPointTransaction createCreditTransaction(
            long customerId,
            BigDecimal points,
            int expiryMonths,
            String note) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "points", points, "monthsToExpiry",
                expiryMonths, "note", note);
        return this.transport.doPost("loyalty/credit", params, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymClientException
     */
    public ModymPointTransaction approveCreditTransaction(long transactionId) throws ModymClientException {
        String path = "loyalty/credit/" + Long.toString(transactionId) + "/approve";
        return this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param transactionId
     * @return
     * @throws ModymClientException
     */
    public ModymPointTransaction rejectCreditTransaction(long transactionId) throws ModymClientException {
        String path = "loyalty/credit/" + Long.toString(transactionId) + "/reject";
        return this.transport.doPut(path, null, null, null, PointTransactionResponse.class).getResult();
    }

    /**
     * @param referenceIds
     * @return
     * @throws ModymClientException
     */
    public List<ModymPurchaseRewardSummary> getPurchasesRewardSummary(List<String> referenceIds)
            throws ModymClientException {
        String path = "loyalty/purchases";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("referenceIds", StringUtils.join(referenceIds, ","));
        return this.transport.doGet(path, parameters, null, PurchaseLoyaltyListResponse.class).getResult();
    }

}
