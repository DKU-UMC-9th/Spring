package com.example.umc9th.domain.user.service.command;

import com.example.umc9th.domain.user.dto.UserReqDTO.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO.UserResDTO;

public interface UserCommandService {
    UserResDTO.JoinDTO signup(UserReqDTO.JoinDTO dto);  // ReqDTO로 받아서 ResDTO로 반환
}