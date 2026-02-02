package com.saimiral.usermanagement.dto;

import java.util.List;

public class PagedResponse<T> {
    List<T> content;
    int page;
    int size;
    long totalElements;
    int totalPages;

    public PagedResponse(List<T> content,
                         int page,
                         int size,
                         long totalElements,
                         int totalPages){
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
