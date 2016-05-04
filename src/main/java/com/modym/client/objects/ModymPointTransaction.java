/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class ModymPointTransaction {

    private long transactionId;
    private long customerId;
    private String type;
    private String status;
    private String referenceId;
    private BigDecimal points;
    private BigDecimal pointValue;
    private String pointValueCurrency;
    private LocalDate date;
    private LocalTime time;
    private String note;

}