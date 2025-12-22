package com.example.umc9th.global.auth.details;

import com.example.umc9th.domain.member.entity.Member;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    // 권한을 List 형태로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> member.getRole().toString());
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // 아이디 반환
    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
