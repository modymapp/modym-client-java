/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.modym.client.objects.ModymPointTransaction.ModymPointCreditTransaction;
import com.modym.client.objects.ModymPointTransaction.ModymPointDebitTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @Type(value = ModymPointCreditTransaction.class, name = "credit"),
        @Type(value = ModymPointDebitTransaction.class, name = "debit") })
@Getter
@Setter
public abstract class ModymPointTransaction {

    private String transactionId;
    private String customerId;
    private String type;
    private String referenceId;
    private BigDecimal points;
    private BigDecimal pointValue;
    private String pointValueCurrency;
    private String note;
    private LocalDateTime timestamp;

    @Getter
    @Setter
    public static class ModymPointCreditTransaction extends ModymPointTransaction {
        private String rewardActionCategory;
        private String rewardActionName;
        private LocalDate expiration;
        private CreditTransactionStatus status;
    }

    @Getter
    @Setter
    public static class ModymPointDebitTransaction extends ModymPointTransaction {
        private DebitTransactionStatus status;
    }

    /**
     *
     */
    public static enum DebitTransactionStatus {
        AUTHORIZED,
        CAPTURED,
        CANCELLED;
    }

    /**
     *
     */
    public static enum CreditTransactionStatus {
        PENDING,
        APPROVED,
        REJECTED,
        VOID;
    }

}
