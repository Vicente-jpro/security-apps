package com.secure.api.configs;

//@Profile("!prod")
//@Component
//@RequiredArgsConstructor
public class EazyBankUsernamePasswordAuthenticationProvider 
/*implements AuthenticationProvider*/ {
/*
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var username = authentication.getName();
        final var password = authentication.getCredentials().toString();

        final var userDetails = userDetailsService.loadUserByUsername(username);
        final var isEqualPassword = passwordEncoder.matches(password, userDetails.getPassword());

        return  new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    */
}
