/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
@Getter
@Setter
public class PageResponse<T> {

    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> content;

}
