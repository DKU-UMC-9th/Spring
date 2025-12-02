package com.example.umc_spring_first.domain.store.controller;

import com.example.umc_spring_first.domain.store.dto.StoreCreateRequest;
import com.example.umc_spring_first.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/stores") //특정 지역에 가게 추가하기 API
    public ResponseEntity<?> addStore(
            @RequestBody StoreCreateRequest req
    ) {
        Long id = storeService.addStore(req);
        return ResponseEntity.ok(id);
    }
}
