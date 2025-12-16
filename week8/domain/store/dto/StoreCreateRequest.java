package com.example.umc_spring_first.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateRequest {
    private String name;
    private String address;
    private String phone;
    private String ownerNumber;
}

