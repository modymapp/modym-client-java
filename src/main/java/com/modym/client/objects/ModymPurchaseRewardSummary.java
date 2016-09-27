/************************************************************************
 * Copyright MODYM, Ltd.
 */

package com.modym.client.objects;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sameer
 */
@Getter
@Setter
public class ModymPurchaseRewardSummary {
    private Long purchaseId;
    private String referenceId;
    private BigDecimal pendingPoints;
    private BigDecimal approvedPoints;
}
