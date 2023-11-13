package com.microcommerce.common.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;

    public Pagination(Page<?> page) {
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = (int) page.getTotalElements();
    }
}
