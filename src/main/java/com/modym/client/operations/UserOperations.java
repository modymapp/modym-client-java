/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.objects.ModymCustomer;
import com.modym.client.response.BooleanResponse;
import com.modym.client.response.CustomerResponse;
import com.modym.client.utils.MapUtils;

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
     * @throws ModymException
     */
    public ModymCustomer authenticate(String email, String password) throws ModymException {
        Map<String, Object> params = MapUtils.asMap("email", email, "password", password);
        CustomerResponse response = this.transport.doPost("users/authenticate", params, null, null, CustomerResponse.class);
        if (!response.isSuccess())
            throw new ModymException(response.getError());
        return response.getResult();
    }

    /**
     * @param customerId
     * @param password
     * @return
     * @throws ModymException
     */
    public boolean register(long customerId, String password) throws ModymException {
        Map<String, Object> params = MapUtils.asMap("customerId", customerId, "password", password);
        return this.transport.doPost("users/register", params, null, null, BooleanResponse.class).getResult();
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws ModymException
     */
    public boolean isRegistered(long customerId) throws ModymException {
        return this.transport.doPost("users/registered/" + customerId, null, null, null, BooleanResponse.class)
                .getResult();
    }

}
