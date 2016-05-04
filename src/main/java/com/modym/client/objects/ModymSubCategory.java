/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class ModymSubCategory {

    private long subcategoryId;

    private String name;

    private String description;

    private long categoryId;

    private String categoryName;

    private boolean active;

}
