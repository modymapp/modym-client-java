/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymException;
import com.modym.client.response.BooleanResponse;
import com.modym.client.utils.MapUtils;

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
    public Boolean sendSms(String phone, String sendAs, String content) throws ModymException {
        Map<String, Object> params = MapUtils.asMap("phoneNumber", phone, "sendAs", sendAs, "content", content);
        return this.transport.doPost("messages/sms", null, params, null, BooleanResponse.class).getResult();
    }
}
