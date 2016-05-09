/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymSubCategory;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class SubCategoryResponse extends ModymResponse {

    private ModymSubCategory result;

}