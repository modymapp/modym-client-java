/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.response;

import java.util.List;

import com.modym.client.objects.ModymCountry;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class CountryListResponse extends ModymResponse {

    private List<ModymCountry> result;

}
