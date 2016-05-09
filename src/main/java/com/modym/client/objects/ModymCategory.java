/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class ModymCategory {

    private long categoryId;

    private String name;

    private String description;

    private List<ModymSubCategory> subcategories;

}
