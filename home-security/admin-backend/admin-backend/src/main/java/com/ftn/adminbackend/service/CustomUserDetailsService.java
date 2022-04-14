package com.ftn.adminbackend.service;


import java.util.Optional;

import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	private User user;


	public CustomUserDetailsService() {
	}


	public CustomUserDetailsService(User user) {
        this.user = user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsername(username);
		if (maybeUser.isPresent()) {
			return maybeUser.get();
		} else {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
    }

	public boolean isEnabled() {
        return user.isEnabled();
    }
    
}
