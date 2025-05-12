package com.luv2code.springboot.demo.mycoolapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // ✅ 所有請求允許
                )
                .csrf(csrf -> csrf.disable())  // ✅ 關掉 CSRF
                .formLogin(login -> login.disable())  // ✅ 不跳出登入畫面
                .httpBasic(basic -> basic.disable());  // ✅ 關掉 Basic 認證
        return http.build();
    }
}
