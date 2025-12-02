package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//- `getAuthorities` : 권한을 List 형태로 반환 (ADMIN or USER)
//- `getPassword` : 비밀번호를 반환
//- `getUsername` : 아이디를 반환 (여기선 이메일을 아이디로 사용!)
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> member.getRole().toString());
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
