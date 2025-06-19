package com.secure.api.configs;

import com.secure.api.exceptionhandlers.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      //  http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http
            .sessionManagement(smc -> smc.invalidSessionUrl("/invalid-session"))
        	// When application is deployed, this line should exist to accept only HTTPS requests.
        	//.requiresChannel(rcc -> rcc.anyRequest().requiresSecure())

        	// This can be used when we are developing the application to accept only HTTP request.
        	.csrf(crsfConfig -> crsfConfig.disable())
        	.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/account","/balance", "/loan").authenticated()
            .requestMatchers("/hello", "/error", "/register", "/invalid-session", "/").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        // This is for monolitic application.
        //  http.exceptionHandling(ehc -> ehc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())
        //    .accessDeniedPage("/denied"));
        http.exceptionHandling(ehc -> ehc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
