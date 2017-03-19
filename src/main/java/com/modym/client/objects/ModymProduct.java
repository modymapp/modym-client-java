/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 */
@Getter
@Setter
public class ModymProduct extends UDFType {

    protected long productId;

    protected String name;

    protected String description;

    protected String sku;

    protected BigDecimal unitPrice;

    protected BigDecimal unitCost;

    protected Long categoryId;

    protected String categoryName;

    protected Long brandId;

    protected String brandName;

    protected Long productGroupId;

    protected String productGroupName;

    protected String productImageUrl;

}
