/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import com.modym.client.objects.ModymUserAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sameer
 */
@Getter
@Setter
public class MosdymUserAuthenticateResponse extends ModymResponse {
    private ModymUserAuthenticationToken result;

}