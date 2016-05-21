/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.response;

import com.modym.client.objects.ModymPurchase;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class PurchaseResponse extends ModymResponse {

    private ModymPurchase result;

}
