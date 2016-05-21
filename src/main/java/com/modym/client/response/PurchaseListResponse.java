/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.response;

import java.util.List;

import com.modym.client.objects.ModymPurchase;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class PurchaseListResponse extends ModymResponse {

    private List<ModymPurchase> result;

}
