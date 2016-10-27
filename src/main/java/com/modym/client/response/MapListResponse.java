/************************************************************************
 * Copyright iMENA Loyalty
 */
package com.modym.client.response;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class MapListResponse extends ModymResponse {

    private List<Map<String, Object>> result;

}