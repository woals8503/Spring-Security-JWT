package com.cos.jwt.config;

import com.cos.jwt.filter.MyFilter1;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 여기서 에러는 필터타입은 시큐리티 타입이 아니기 때문에 필터 걸고싶으면 addFilterBefore or addFilterAfter로 설정
        // 그래서 필터가 실행되기 이전에 필터를 걸어라
        http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // 세션을 사용하지 않고 STATELESS로 만들겠다.
                .and()
                .addFilter(corsConfig.corsFilter())  // 모든 요청은 이 필터를 타게된다. 그래서 Cors 정책에서 벗어날 수 있다.
                // 인증x -> @CrossOrigin, 인증 o -> 필터 등록
                .formLogin().disable()  // jwt서버니까 id, pwd form 로그인을 하지 않음
                .httpBasic().disable()  // Header에 토큰 넣는 방식이라 비활성화로 설정함
                .authorizeRequests()
                .antMatchers("/api/v1/user/**") // 이쪽으로 주소가 들어오면
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이러한 권한을 가져야 한다.
                .antMatchers("/api/v1/manager/**") // 이쪽으로 주소가 들어오면
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이러한 권한을 가져야 한다.
                .antMatchers("/api/v1/admin/**") // 이쪽으로 주소가 들어오면
                .access("hasRole('ROLE_ADMIN')")    // 이러한 권한을 가져야 한다.
                .anyRequest().permitAll();   // 다른 요청은 권한없이 들어갈 수 있다.
        // 여기까지가 form방식 로그인을 하지 않는 방식

    }
}
