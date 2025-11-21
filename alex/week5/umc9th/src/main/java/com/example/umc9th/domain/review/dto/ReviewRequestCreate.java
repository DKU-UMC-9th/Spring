package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record ReviewRequestCreate(
    @NotNull @Min(0)
    Long userId,
    @NotNull @Min(0)
    Long storeId,
    @NotBlank
    String content,
    @NotNull
    Float star
) {}