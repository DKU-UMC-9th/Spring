package com.example.umc9th.global.common;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PageResponse<T>(
    List<T> content,
    int currentPage,
    int pageSize,
    long totalElements,
    int totalPages,
    boolean hasNext,
    boolean hasPrevious
) {
    public static <T> PageResponse<T> of(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .currentPage(page.getNumber() + 1)  // 0-based -> 1-based 변환
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }
}
