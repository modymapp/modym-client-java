/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.response.BooleanResponse;
import com.modym.client.response.StringResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class MessageOperations extends AbstractOperations {

    /**
     * @param transport
     */
    public MessageOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * MESSAGING CALLS
     */

    /**
     * @param phone
     * @param sendAs
     * @param content
     * @return true if SMS sent, false otherwise
     * @throws ModymClientException
     */
    public Boolean sendSms(String phone, String sendAs, String content) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("phoneNumber", phone, "sendAs", sendAs, "content", content);
        return this.transport.doPost("messages/sms", null, params, null, BooleanResponse.class).getResult();
    }

    
    /**
     * @param customerId
     * @param templateId
     * @param context
     * @return modym message id
     * @throws ModymClientException
     */
    public String sendEmail(String customerId, String templateId, Map<String, Object> context) throws ModymClientException {
        return sendEmail(customerId, templateId, null, context);
    }

    
    /**
     * @param customerId
     * @param templateId
     * @param accountId
     * @param context
     * @return modym message id
     * @throws ModymClientException
     */
    public String sendEmail(String customerId, String templateId, String accountId, Map<String, Object> context) throws ModymClientException {
        Map<String, Object> params = ModymMapUtils.asMap("customerId", customerId, "templateId", templateId, "accountId", accountId);
        if( context != null){
            params.putAll(context);    
        }
        return this.transport.doPost("messages/email", null, params, null, StringResponse.class).getResult();
    }
}
