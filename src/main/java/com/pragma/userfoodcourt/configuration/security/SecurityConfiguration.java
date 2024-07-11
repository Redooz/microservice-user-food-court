package com.pragma.userfoodcourt.configuration.security;

import com.pragma.userfoodcourt.configuration.security.filter.JwtAuthenticationFilter;
import com.pragma.userfoodcourt.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register/owner").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/auth/register/employee").hasAnyAuthority(Role.OWNER.name())
                .antMatchers(HttpMethod.POST, "/auth/register/customer").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/users/**").hasAnyAuthority(Role.ADMIN.name(), Role.OWNER.name(), Role.EMPLOYEE.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
