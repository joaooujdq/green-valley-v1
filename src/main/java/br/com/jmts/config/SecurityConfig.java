package br.com.jmts.config;

import br.com.jmts.security.jwt.JwtConfigurer;
import br.com.jmts.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("", 8, 18500, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256 );
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.httpBasic().disable().csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .authorizeHttpRequests(
                               authorizeHttpRequests -> authorizeHttpRequests
                                    .requestMatchers("/auth/signin", "/auth/refresh/**",  "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                    .requestMatchers("/api/v1/**").authenticated()
                                    .requestMatchers("/users").denyAll()
                        ).cors().and().apply(new JwtConfigurer(tokenProvider));
    }


}
