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
    
    private static final String FIELD_PSWD = "password";
    private static final String FIELD_EMAIL = "email";

    /**
     * @param transport
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
        Map<String, Object> params = ModymMapUtils.asMap(FIELD_EMAIL, email, FIELD_PSWD, password);
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
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, FIELD_PSWD, password);
        return this.transport.doPost("users/register", params, null, null, BooleanResponse.class).getResult();
    }
    
    /**
     * @param customerId
     * @param newPassword
     * @return
     * @throws ModymClientException
     */
    public boolean resetPassword(long customerId, String newPassword) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, FIELD_PSWD, newPassword);
        return this.transport.doPost("users/reset-password", params, null, null, BooleanResponse.class).getResult();
    }

    /**
     * @param customerId
     * @return
     * @throws ModymClientException
     */
    public boolean isRegistered(long customerId) throws ModymClientException {
        return this.transport.doPost("users/registered/" + customerId, null, null, null, BooleanResponse.class)
                .getResult();
    }

    /**
     * @param email
     * @return
     * @throws ModymClientException 
     */
    public ModymCustomer getRegisteredCustomerByEmail(String email) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap(FIELD_EMAIL, email);
        return this.transport.doPost("users/registered/email", params, null, null, CustomerResponse.class).getResult();
    }

}
