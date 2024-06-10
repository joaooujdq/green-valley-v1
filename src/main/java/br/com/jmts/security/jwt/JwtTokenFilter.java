package br.com.jmts.security.jwt;


import br.com.jmts.exceptions.InvalidJwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private  JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Entrou DoFilter");
        String token = tokenProvider.resolveToken((HttpServletRequest) request);
        System.out.println("teste0");
            System.out.println("1" + token + "valdiate token" );
            if(token != null && tokenProvider.validateToken(token)){
                System.out.println("refresh token1" );
                Authentication auth = tokenProvider.getAuthentication(token);
                if(auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }


        chain.doFilter(request, response);
    }
}
