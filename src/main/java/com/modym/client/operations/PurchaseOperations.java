/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymPurchase;
import com.modym.client.response.PurchaseListResponse;
import com.modym.client.response.PurchaseResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class PurchaseOperations extends AbstractOperations {

    /**
     * @param transport
     */
    public PurchaseOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * PURCHASE API CALLS
     */

    
    /**
     * @param purchaseId
     * @return 
     * @throws ModymClientException
     */
    public ModymPurchase getPurchase(long purchaseId) throws ModymClientException {
        String path = "purchases/" + Long.toString(purchaseId);
        return this.transport.doGet(path, null, null, PurchaseResponse.class).getResult();
    }

    /**
     * @param purchaseReferenceId
     * @return
     * @throws ModymClientException
     */
    public ModymPurchase getPurchaseByReferenceId(String purchaseReferenceId) throws ModymClientException {
        String path = "purchases/referenceId/" + purchaseReferenceId;
        return this.transport.doGet(path, null, null, PurchaseResponse.class).getResult();
    }

    /**
     * @param customerId
     * @param sourceId
     * @param purchase
     * @return
     * @throws ModymClientException
     */
    public ModymPurchase addPurchase(long customerId, long sourceId, Map<String, Object> purchase)
            throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "sourceId", sourceId);
        return this.transport.doPost("purchases", params, purchase, null, PurchaseResponse.class).getResult();
    }

    /**
     * @param purchaseId
     * @param purchase
     * @return
     * @throws ModymClientException
     */
    public ModymPurchase updatePurchase(long purchaseId, Map<String, Object> purchase) throws ModymClientException {
        String path = "purchases/" + Long.toString(purchaseId);
        return this.transport.doPut(path, null, purchase, null, PurchaseResponse.class).getResult();
    }

    /**
     * @param referenceIds
     * @return
     * @throws ModymClientException
     */
    public List<ModymPurchase> getPurchaseList(List<String> referenceIds) throws ModymClientException {
        String path = "purchases/list/items";
        Map<String, Object> parameters= new HashMap<>();
        parameters.put("referenceIds", concat(referenceIds, ","));
        return this.transport.doGet(path, parameters, null, PurchaseListResponse.class).getResult();
    }

    /**
     * @param items
     * @param separotor
     * @return
     */
    private String concat(List<String> items, String separotor) {
        StringBuilder builder= new StringBuilder();
        boolean first = true;
        for ( String item : items ){
            if ( !first ){
                builder.append(separotor);
            }
            builder.append(item);
            first = false;
        }
        return builder.toString();
    }
}
