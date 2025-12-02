package com.example.umc9th.domain.store.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreReqDTO {

    @Getter
    public static class CreateStoreDTO {
        @NotBlank(message = "가게 이름은 필수입니다.")
        private String name;

        private String detailAddress;

        @NotNull(message = "지역 ID는 필수입니다.")
        private Long locationId;
    }
}