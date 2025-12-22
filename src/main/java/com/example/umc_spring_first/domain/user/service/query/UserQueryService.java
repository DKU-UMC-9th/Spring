package com.example.umc_spring_first.domain.user.service.query;

import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;
import jakarta.validation.Valid;

public interface UserQueryService {

    UserResDTO.LoginDTO login(@Valid UserReqDTO.LoginDTO dto);
}