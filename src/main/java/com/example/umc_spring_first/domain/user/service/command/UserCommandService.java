package com.example.umc_spring_first.domain.user.service.command;

import com.example.umc_spring_first.domain.user.dto.req.UserReqDTO;
import com.example.umc_spring_first.domain.user.dto.res.UserResDTO;

public interface UserCommandService {
    UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto);
}