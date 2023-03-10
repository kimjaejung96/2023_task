package com.tredlinx.task.common.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tredlinx.task.common.component.JwtUtils;
import com.tredlinx.task.common.exception.handler.AuthenticationHandler;
import com.tredlinx.task.common.exception.handler.AuthorizationHandler;
import com.tredlinx.task.common.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationHandler authenticationHandler;
    private final AuthorizationHandler authorizationHandler;
    private final String[] permitUriList =
            {"/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger*/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .formLogin() .disable()
                .cors().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationHandler)
                .accessDeniedHandler(authorizationHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()


                .antMatchers(HttpMethod.POST,"/v1/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/v1/signin").permitAll()
                .antMatchers( permitUriList).permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthenticationFilter(objectMapper, jwtUtils), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();

    }
}
