/************************************************************************ 
 * Copyright MODYM, Ltd.
 */

package com.modym.client.operations;

import java.util.List;
import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymCustomer;
import com.modym.client.objects.ModymPurchase;
import com.modym.client.response.CustomerResponse;
import com.modym.client.response.PurchaseListResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class CustomerOperations extends AbstractOperations {

    /**
     * 
     */
    public CustomerOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * CUSTOMER API CALLS
     */

    public ModymCustomer getCustomer(long id) throws ModymClientException {
        String path = "customers/" + Long.toString(id);
        return this.transport.doGet(path, null, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer getCustomerByReferenceId(String referenceId) throws ModymClientException {
        String path = "customers/lookup";
        Map<String, Object> parameters = ModymMapUtils.asMap("method", "reference", "query", referenceId);
        return this.transport.doGet(path, parameters, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer getCustomerByEmail(String email) throws ModymClientException {
        Map<String, Object> parameters = ModymMapUtils.asMap("method", "email", "query", email);
        return this.transport.doGet("customers/lookup", parameters, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer lookupCustomer(String reference) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("method", "reference", "query", reference);
        return this.transport.doGet("customers/lookup", params, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer addCustomer(Map<String, Object> customer) throws ModymClientException {
        Map<String, Object> params = customer;
        return this.transport.doPost("customers", null, params, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer updateCustomer(long customerId, Map<String, Object> customer) throws ModymClientException {
        String path = "customers/" + Long.toString(customerId);
        return this.transport.doPut(path, null, customer, null, CustomerResponse.class).getResult();
    }

    public List<ModymPurchase> getCustomerPurchases(long customerId) throws ModymClientException {
        String path = "customers/" + Long.toString(customerId) + "/purchases";
        return this.transport.doGet(path, null, null, PurchaseListResponse.class).getResult();
    }

}