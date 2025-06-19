package com.secure.api.services;

import com.secure.api.models.Customer;
import com.secure.api.repositories.CustomerRepository;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        final var customer = customerRepository.findByEmail(username)
            .orElseThrow(()-> new UsernameNotFoundException("email invalido."));

        final var authorities = customer.getAuthorities()
                .stream()
                .map(at -> new SimpleGrantedAuthority(at.getName().getValue()))
                .collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPasswrd(), authorities);
    }

    public Customer save(@NonNull Customer customer) {
    	customerRepository.save(customer);
    	return customer;
    }

}
