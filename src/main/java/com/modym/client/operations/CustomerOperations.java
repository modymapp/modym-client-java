/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.objects.ModymCustomer;
import com.modym.client.response.CustomerResponse;
import com.modym.client.utils.MapUtils;

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

    public ModymCustomer getCustomer(long id) throws ModymException {
        String path = "customers/" + Long.toString(id);
        return this.transport.doGet(path, null, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer getCustomerByReferenceId(String referenceId) throws ModymException {
        String path = "customers/lookup";
        Map<String, Object> parameters = MapUtils.asMap("method", "reference", "query", referenceId);
        return this.transport.doGet(path, parameters, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer lookupCustomer(String reference) throws ModymException {
        Map<String, Object> params = MapUtils.asMap("method", "reference", "query", reference);
        return this.transport.doGet("customers/lookup", params, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer addCustomer(Map<String, Object> customer) throws ModymException {
        Map<String, Object> params = customer;
        return this.transport.doPost("customers", null, params, null, CustomerResponse.class).getResult();
    }

    public ModymCustomer updateCustomer(long customerId, Map<String, Object> customer) throws ModymException {
        Map<String, Object> params = customer;
        return this.transport.doPost("customers", null, params, null, CustomerResponse.class).getResult();
    }

}
