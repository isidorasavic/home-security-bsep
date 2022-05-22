package com.ftn.MyHousebackend.service;

import java.util.List;
import java.util.Optional;

import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user;
    }

    public List<User> findAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndDeletedIsFalse(username);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("No such user exists");
    }


}
