package com.shahkaar.oidcgcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {
    
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests(a -> a
            .antMatchers("/").permitAll()
            .mvcMatchers("/user", "oidc-user")
            	.hasAnyAuthority("SCOPE_openid")
            .anyRequest().authenticated())
        .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .logout(l -> l.logoutSuccessUrl("/").permitAll())
        .oauth2Login(o -> o
            .successHandler((request, response, authentication) -> {
                response.sendRedirect("/");
            }));
		
        return http.build();
    }
}