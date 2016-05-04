/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bashar
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BigDecimalResponse extends ModymResponse {
    private BigDecimal result;
}