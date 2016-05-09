/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class ModymProductGroup {

    private long productGroupId;

    private String name;

    private String description;

    private boolean active;

    private String referenceId;

}
