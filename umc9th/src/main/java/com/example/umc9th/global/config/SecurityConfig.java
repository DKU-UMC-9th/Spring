package com.example.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//보안 정책 정의
@EnableWebSecurity  //SpringSecurity 설정 활성화
@Configuration      //
public class SecurityConfig {

    private final String[] allowUris = {
            "/sign-up"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(allowUris).permitAll()

                // Swagger 전체 admin 전용
                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**")
                .hasRole("ADMIN")

                .anyRequest().authenticated()
        );

        http.formLogin(form -> form     //세션 생성
                .defaultSuccessUrl("/swagger-ui/index.html", true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(200);
                })
                .permitAll()
        );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}