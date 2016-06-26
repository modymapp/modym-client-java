/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymCustomer;
import com.modym.client.response.BooleanResponse;
import com.modym.client.response.CustomerResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class UserOperations extends AbstractOperations {

    /**
     * 
     */
    public UserOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * USER API CALLS
     */

    /**
     * @param email
     * @param password
     * @return
     * @throws ModymClientException
     */
    public ModymCustomer authenticate(String email, String password) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("email", email, "password", password);
        CustomerResponse response = this.transport.doPost("users/authenticate", params, null, null, CustomerResponse.class);
        if (!response.isSuccess())
            throw new ModymClientException(response.getError());
        return response.getResult();
    }

    /**
     * @param customerId
     * @param password
     * @return
     * @throws ModymClientException
     */
    public boolean register(long customerId, String password) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "password", password);
        return this.transport.doPost("users/register", params, null, null, BooleanResponse.class).getResult();
    }
    
    /**
     * @param customerId
     * @param password
     * @return
     * @throws ModymClientException
     */
    public boolean resetPassword(long customerId, String newPassword) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "password", newPassword);
        return this.transport.doPost("users/reset-password", params, null, null, BooleanResponse.class).getResult();
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws ModymClientException
     */
    public boolean isRegistered(long customerId) throws ModymClientException {
        return this.transport.doPost("users/registered/" + customerId, null, null, null, BooleanResponse.class)
                .getResult();
    }

}
