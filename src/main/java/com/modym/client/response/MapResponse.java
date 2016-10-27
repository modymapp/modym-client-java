/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bashar
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MapResponse extends ModymResponse {
    private Map<String, Object> result;
}