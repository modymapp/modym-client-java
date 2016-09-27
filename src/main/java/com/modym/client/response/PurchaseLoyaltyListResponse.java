/************************************************************************
 * Copyright MODYM, Ltd.
 */

package com.modym.client.response;

import java.util.List;

import com.modym.client.objects.ModymPurchaseRewardSummary;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sameer
 */
@Getter
@Setter
public class PurchaseLoyaltyListResponse extends ModymResponse {

    private List<ModymPurchaseRewardSummary> result;
}
