package com.example.umc_spring_first.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Getter
    @Builder
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}

