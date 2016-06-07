/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
        Map<String, Object> params = new HashMap<>();
        params.put("page", 0);
        params.put("size", 10);
        return this.transport.doGet(path, params, null, CategoryPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymCategory> getCategories(int page, int size) throws ModymClientException {
        if (page < 0)
            throw new ModymClientException("Page number cannot be less than 0");
        if (size < 1)
            throw new ModymClientException("Page size cannot be less than 1");

        String path = "catalog/categories";
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        return this.transport.doGet(path, params, null, CategoryPageResponse.class).getResult();
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
    public PageResponse<ModymProduct> getProducts(
            int page,
            int size,
            String sort,
            boolean ascending,
            boolean includeInactive) throws ModymClientException {
        String path = "catalog/products/search";
        Map<String, Object> params = new HashMap<>();
        params.put("method", "all");
        params.put("id", "0");
        params.put("page", page);
        params.put("size", size);
        params.put("includeInActive", includeInactive);
        if (StringUtils.isNotBlank(sort)) {
            params.put("sort", sort);
            params.put("direction", ascending ? "ASC" : "DESC");
        }
        return this.transport.doGet(path, params, null, ProductPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @param page
     * @param size
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getFeaturedProducts(
            int page,
            int size,
            String sort,
            boolean ascending,
            boolean includeInactive) throws ModymClientException {
        String path = "catalog/products/search";
        Map<String, Object> params = new HashMap<>();
        params.put("method", "featured");
        params.put("id", "0");
        params.put("page", page);
        params.put("size", size);
        params.put("includeInActive", includeInactive);
        if (StringUtils.isNotBlank(sort)) {
            params.put("sort", sort);
            params.put("direction", ascending ? "ASC" : "DESC");
        }
        return this.transport.doGet(path, params, null, ProductPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @param page
     * @param size
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getCategoryProducts(long categoryId, int page, int size)
            throws ModymClientException {
        String path = "catalog/products/search";
        Map<String, Object> params =
                ModymMapUtils.asMap("method", "category", "id", categoryId, "page", page, "size", size);
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
