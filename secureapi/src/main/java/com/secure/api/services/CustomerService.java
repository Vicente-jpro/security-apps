package com.secure.api.services;

import com.secure.api.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var customer = customerRepository.findByEmail(username)
            .orElseThrow(()-> new UsernameNotFoundException("email invalido."));


        final var authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));

        return new User(customer.getEmail(), customer.getPasswrd(), authorities);
    }
}
