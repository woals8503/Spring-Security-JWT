package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    // SecurityConfig에 필터를 등록해줘야한다.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 내 서버가 응답할때 json을 자바스크립트에서 처리할 수 있게 할지를 설정    false라면 자바스크립트로 요청했을때 오지 않는다.
        config.addAllowedOrigin("*");       // 모든 ip에 응답을 허용
        config.addAllowedHeader("*");       // 모든 header에 응답을 허용
        config.addAllowedMethod("*");       // 모든 post, get, put, delete, patch 응답을 허용
        source.registerCorsConfiguration("/api/**", config);    // 이런 주소로 들어오는 모든 요청은 config를 따라라
        return new CorsFilter(source);
    }
}
