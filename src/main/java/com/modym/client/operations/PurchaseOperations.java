/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.objects.ModymPurchase;
import com.modym.client.response.PurchaseResponse;
import com.modym.client.utils.MapUtils;

/**
 * @author bashar
 *
 */
public class PurchaseOperations extends AbstractOperations {

    /**
     * 
     */
    public PurchaseOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * PURCHASE API CALLS
     */

    public ModymPurchase getPurchase(long purchaseId) throws ModymException {
        String path = "purchases/" + Long.toString(purchaseId);
        return this.transport.doGet(path, null, null, PurchaseResponse.class).getResult();
    }

    public ModymPurchase getPurchaseByReferenceId(String purchaseReferenceId) throws ModymException {
        String path = "purchases/referenceId/" + purchaseReferenceId;
        return this.transport.doGet(path, null, null, PurchaseResponse.class).getResult();
    }

    public ModymPurchase addPurchase(long customerId, long sourceId, Map<String, Object> purchase)
            throws ModymException {
        Map<String, Object> params = MapUtils.asMap("customerId", customerId, "sourceId", sourceId);
        return this.transport.doPost("purchases", params, purchase, null, PurchaseResponse.class).getResult();
    }

    public ModymPurchase updatePurchase(long purchaseId, Map<String, Object> purchase) throws ModymException {
        String path = "purchases/" + Long.toString(purchaseId);
        return this.transport.doPut(path, null, purchase, null, PurchaseResponse.class).getResult();
    }

}
