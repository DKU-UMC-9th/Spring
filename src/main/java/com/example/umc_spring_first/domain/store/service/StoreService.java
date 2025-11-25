package com.example.umc_spring_first.domain.store.service;

import com.example.umc_spring_first.domain.store.dto.StoreCreateRequest;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

    public Long addStore(StoreCreateRequest req) {

        Store store = Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                .phone(req.getPhone())
                .ownerNumber(req.getOwnerNumber())
                .build();

        storeRepository.save(store);
        return store.getId();
    }
}
