/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.math.BigDecimal;

import com.modym.client.ModymException;
import com.modym.client.response.BigDecimalResponse;
import com.modym.client.response.StringResponse;

/**
 * @author bashar
 *
 */
public class SystemOperations extends AbstractOperations {

    /**
     * 
     */
    public SystemOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * SYSTEM CALLS
     */

    public String getProgramName() throws ModymException {
        return "";
    }

    public String getSystemCurrency() throws ModymException {
        return this.transport.doGet("system/currency", null, null, StringResponse.class).getResult();
    }

    public BigDecimal getSystemPointConversionRate() throws ModymException {
        return this.transport.doGet("system/pointConversionRate", null, null, BigDecimalResponse.class).getResult();
    }

}
