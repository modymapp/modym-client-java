/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymCategory;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class CategoryPageResponse extends ModymResponse {

    private PageResponse<ModymCategory> result;

}