/************************************************************************
 * Copyright modym
 */
package com.modym.client.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class ModymCountry {

    private long countryId;

    private String name;

    private String iso2;

    private String iso3;

    private int dialingCode;

    public String getDisplayName() {
        return this.name + " +" + dialingCode;
    }
}
