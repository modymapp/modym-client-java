/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.util.HashMap;
import java.util.Map;

import com.modym.client.ModymClientException;
import com.modym.client.objects.ModymCategory;
import com.modym.client.objects.ModymProduct;
import com.modym.client.response.CategoryPageResponse;
import com.modym.client.response.CategoryResponse;
import com.modym.client.response.PageResponse;
import com.modym.client.response.ProductPageResponse;
import com.modym.client.response.ProductResponse;

import lombok.Getter;

/**
 * @author bashar
 *
 */
public class CatalogOperations extends AbstractOperations {

    private static final String PATH_CATEGORIES = "catalog/categories";
    private static final String PATH_PRODUCTS = "catalog/products";
    private static final String PATH_PRODUCTS_SEARCH = PATH_PRODUCTS + "/search";

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
        Map<String, Object> params = new HashMap<>();
        params.put("page", 0);
        params.put("size", 10);
        return this.transport.doGet(PATH_CATEGORIES, params, null, CategoryPageResponse.class).getResult();
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

        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        return this.transport.doGet(PATH_CATEGORIES, params, null, CategoryPageResponse.class).getResult();
    }

    /**
     * @param categoryId
     * @return
     * @throws ModymClientException
     */
    public ModymCategory getCategory(long categoryId) throws ModymClientException {
        String path = PATH_CATEGORIES + "/" + categoryId;
        return this.transport.doGet(path, null, null, CategoryResponse.class).getResult();
    }

    /*******************************************************************************************************************
     * PRODUCT CALLS
     */

    /**
     * @param productId
     * @return
     * @throws ModymClientException
     */
    public ModymProduct getProduct(long productId) throws ModymClientException {
        String path = PATH_PRODUCTS + "/" + productId;
        return this.transport.doGet(path, null, null, ProductResponse.class).getResult();
    }

    /**
     * @param page
     * @param size
     * @param sort
     * @param ascending
     * @param includeInactive
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getAllProducts(int page, int size, ProductSort sort, boolean includeInactive)
            throws ModymClientException {
        return this.getProducts("all", null, page, size, sort, includeInactive);
    }

    /**
     * @param categoryId
     * @param page
     * @param size
     * @param sort
     * @param ascending
     * @param includeInactive
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getCategoryProducts(
            long categoryId,
            int page,
            int size,
            ProductSort sort,
            boolean includeInactive) throws ModymClientException {
        return this.getProducts("category", categoryId, page, size, sort, includeInactive);
    }


    /**
     * @param page
     * @param size
     * @param sort
     * @param ascending
     * @param includeInactive
     * @return
     * @throws ModymClientException
     */
    public PageResponse<ModymProduct> getFeaturedProducts(int page, int size, ProductSort sort, boolean includeInactive)
            throws ModymClientException {
        return this.getProducts("featured", null, page, size, sort, includeInactive);
    }

    /**
     * @param method
     * @param id
     * @param page
     * @param size
     * @param sort
     * @param ascending
     * @param includeInactive
     * @return
     * @throws ModymClientException
     */
    private PageResponse<ModymProduct> getProducts(
            String method,
            Long id,
            int page,
            int size,
            ProductSort sort,
            boolean includeInactive) throws ModymClientException {

        if (method == null || method.trim().length() == 0)
            throw new ModymClientException("method cannot be null or empty");

        Long searchId = 0L;
        if (id != null)
            searchId = id;

        Map<String, Object> params = new HashMap<>();
        params.put("method", method);
        params.put("id", searchId);
        params.put("page", page);
        params.put("size", size);
        params.put("includeInActive", includeInactive);
        if (sort != null) {
            params.put("sort", sort.getField());
            params.put("direction", sort.getDirection());
        }

        return this.transport.doGet(PATH_PRODUCTS_SEARCH, params, null, ProductPageResponse.class).getResult();
    }

    @Getter
    public enum ProductSort {
        NONE(null, null),
        RECENTLY_ADDED("createTimestamp", "DESC"),
        RECENTLY_UPDATED("updateTimestamp", "DESC"),
        PRICE_HL("price", "DESC"),
        PRICE_LH("price", "ASC");

        private String field;
        private String direction;

        private ProductSort(String field, String direction) {
            this.field = field;
            this.direction = direction;
        }
    }

}
