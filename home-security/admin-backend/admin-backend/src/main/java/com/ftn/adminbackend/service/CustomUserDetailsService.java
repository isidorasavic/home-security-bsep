package com.ftn.adminbackend.service;

import com.ftn.adminbackend.exception.UserNotFoundException;
import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepository userRepository;

	private User user;

	public CustomUserDetailsService() {
	}


	public CustomUserDetailsService(User user) {
        this.user = user;
    }


	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsernameAndDeletedIsFalseAndBlockedIsFalse(username);
		if (maybeUser.isPresent()) {
			return maybeUser.get();
		} else {
			throw new UserNotFoundException(String.format("No user found with username '%s'.", username));
		}
    }
}
