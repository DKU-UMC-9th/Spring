package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
