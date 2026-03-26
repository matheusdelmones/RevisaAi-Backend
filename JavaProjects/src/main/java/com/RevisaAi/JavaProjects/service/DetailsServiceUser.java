package com.RevisaAi.JavaProjects.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RevisaAi.JavaProjects.repository.UserRepository;

@Service
public class DetailsServiceUser implements UserDetailsService { 

    private final UserRepository userRepository;

    public DetailsServiceUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário nao encontrado com email: " + username));
    }
    
}
