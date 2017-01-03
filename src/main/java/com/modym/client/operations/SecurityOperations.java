/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymUserAuthenticationToken;
import com.modym.client.response.MosdymUserAuthenticateResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author Sameer
 *
 */
public class SecurityOperations extends AbstractOperations {

    /**
     * @param transport
     */
    public SecurityOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * SECURITY API CALLS
     */

    /**
     * @param username
     * @param password
     * @return
     * @throws ModymClientException
     */
    public ModymUserAuthenticationToken authenticate(String username, String password) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("username", username, "password", password);
        MosdymUserAuthenticateResponse response = this.transport.doPost("security/authenticate", null, params, null, MosdymUserAuthenticateResponse.class);
        if (!response.isSuccess())
            throw new ModymClientException(response.getError());
        return response.getResult();
    }
}
