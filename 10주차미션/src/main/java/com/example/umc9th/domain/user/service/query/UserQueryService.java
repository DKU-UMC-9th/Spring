package com.example.umc9th.domain.user.service.query;

import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;
import jakarta.validation.Valid;

public interface UserQueryService {

    UserResDTO.LoginDTO login(@Valid UserReqDTO.LoginDTO dto);
}
