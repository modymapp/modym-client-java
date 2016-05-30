/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.response.BooleanResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class MessageOperations extends AbstractOperations {

    /**
     * 
     */
    public MessageOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * MESSAGING CALLS
     */
    public Boolean sendSms(String phone, String sendAs, String content) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("phoneNumber", phone, "sendAs", sendAs, "content", content);
        return this.transport.doPost("messages/sms", null, params, null, BooleanResponse.class).getResult();
    }
}
