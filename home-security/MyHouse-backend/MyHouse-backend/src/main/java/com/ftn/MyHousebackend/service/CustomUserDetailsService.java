package com.ftn.MyHousebackend.service;

import java.util.Optional;

import com.ftn.MyHousebackend.exception.UserNotFoundException;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

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
			throw new UserNotFoundException(String.format("No user found with username '%s'.", username));
		}
    }
}
