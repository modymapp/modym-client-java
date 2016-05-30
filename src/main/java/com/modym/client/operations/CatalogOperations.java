/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymCategory;
import com.modym.client.objects.ModymProduct;
import com.modym.client.objects.ModymSubCategory;
import com.modym.client.response.CategoryPageResponse;
import com.modym.client.response.CategoryResponse;
import com.modym.client.response.PageResponse;
import com.modym.client.response.ProductPageResponse;
import com.modym.client.response.ProductResponse;
import com.modym.client.response.SubCategoryResponse;
import com.modym.client.utils.ModymMapUtils;

/**
 * @author bashar
 *
 */
public class CatalogOperations extends AbstractOperations {

    /**
     * 
     */
    public CatalogOperations(ModymApiTransport transport) {
        super(transport);
    }

    /*******************************************************************************************************************
     * CATALOG CALLS
     */

    /**
     * @param categoryId
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymCategory> getCategories() throws ModymClientException {
        String path = "catalog/categories";
        return this.transport.doGet(path, null, null, CategoryPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @return
     * @throws ModymClientException
     */
    public ModymCategory getCategory(long categoryId) throws ModymClientException {
        String path = "catalog/categories/" + categoryId;
        return this.transport.doGet(path, null, null, CategoryResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @return
     * @throws ModymClientException
     */
    public ModymSubCategory getSubCategory(long subcategoryId) throws ModymClientException {
        String path = "catalog/subcategories/" + subcategoryId;
        return this.transport.doGet(path, null, null, SubCategoryResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @param page
     * @param size
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getCategoryProducts(long categoryId, int page, int size) throws ModymClientException {
        String path = "catalog/products/search";
        Map<String, Object> params = ModymMapUtils.asMap("method", "category", "id", categoryId, "page", page, "size", size);
        return this.transport.doGet(path, params, null, ProductPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @param page
     * @param size
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getSubcategoryProducts(long subcategoryId, int page, int size)
            throws ModymClientException {
        String path = "catalog/products/search";
        Map<String, Object> params =
                ModymMapUtils.asMap("method", "subcategory", "id", subcategoryId, "page", page, "size", size);
        return this.transport.doGet(path, params, null, ProductPageResponse.class).getResult();
    }

    /**
     * @param productId
     * @return
     * @throws ModymClientException
     */
    public ModymProduct getProduct(long productId) throws ModymClientException {
        String path = "catalog/products/" + productId;
        return this.transport.doGet(path, null, null, ProductResponse.class).getResult();
    }

}
