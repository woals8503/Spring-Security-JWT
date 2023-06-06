package com.cos.jwt.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터1");
        // 이거 설정 안하면 필터 한번 실행되고 끝남
        chain.doFilter(request, response);  // 다시 필터 체인하라
    }
}
