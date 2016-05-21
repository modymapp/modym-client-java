/************************************************************************ 
 * Copyright MODYM, Ltd.
 * 
 */

package com.modym.client.response;

import java.util.List;

import com.modym.client.objects.ModymCity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class CityListResponse extends ModymResponse {

    private List<ModymCity> result;

}
