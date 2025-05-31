package com.secure.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiReactivePasswordChecker;

import java.security.Principal;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
      //  http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/account","/balance", "/loan").authenticated()
            .requestMatchers("/hello", "/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        final var password = new BCryptPasswordEncoder();
        password.encode("this password");
        password.matches("this password", "password from database");

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /* Used to check the user password if it is eases password or not.
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
    */
}
