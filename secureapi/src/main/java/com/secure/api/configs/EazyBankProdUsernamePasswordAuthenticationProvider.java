package com.secure.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EazyBankProdUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired(required = true)
    private UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();

        final var userDetails = userDetailsService.loadUserByUsername(username);
        final var isEqualPassword = passwordEncoder.matches(password, userDetails.getPassword());

        if (isEqualPassword) {
            // Here I can perform validation to check everything that I want.
            // Ex: Fetch age details and perform validation to check if age > 18
          return  new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Username/Password invalid.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
