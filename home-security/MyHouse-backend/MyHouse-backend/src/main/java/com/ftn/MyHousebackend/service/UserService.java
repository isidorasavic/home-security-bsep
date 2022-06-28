package com.ftn.MyHousebackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import com.ftn.MyHousebackend.dto.UserDTO;
import com.ftn.MyHousebackend.exception.RoleNotFound;
import com.ftn.MyHousebackend.exception.UserAlreadyExists;
import com.ftn.MyHousebackend.exception.UserNotFoundException;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.model.enums.UserRole;
import com.ftn.MyHousebackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService implements UserDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
	private PasswordEncoder passwordEncoder;


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

    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        userRepository.findByDeletedIsFalseAndRoleIsNot(UserRole.ADMIN).iterator().forEachRemaining(user -> users.add(new UserDTO(user)));
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndDeletedIsFalseAndBlockedIsFalse(username);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("User not found!");
    }

    public List<UserDTO> searchUsers(String searchWord){
        List<UserDTO> users = new ArrayList<UserDTO>();
        userRepository.findByRoleNotAndDeletedIsFalseAndUsernameContaining(UserRole.ADMIN, searchWord).iterator().forEachRemaining(user -> users.add(new UserDTO(user)));
        return users;
    }

    public UserDTO deleteUser(long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        else{
            User user = userOptional.get();
            user.setDeleted(true);
            userRepository.saveAndFlush(user);
            return new UserDTO(userOptional.get());
        }
    }

    public UserDTO changeRole(String username,String newRole){
        if(!newRole.equals("OWNER") && !newRole.equalsIgnoreCase("TENANT")){
            throw new RoleNotFound("Role not found!");
        }
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        else{
            User user = userOptional.get();
            user.setRole(UserRole.valueOf(newRole));
            userRepository.saveAndFlush(user);
            return new UserDTO(user);
        }
    }

    public UserDTO addUser(UserDTO userDTO){
        LOG.info("Recived request to add user: "+userDTO.toString());
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new UserAlreadyExists("Username "+userDTO.getUsername()+" is taken!");
        }
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setDeleted(false);
        newUser.setRole(UserRole.valueOf(userDTO.getRole()));
        newUser.setBlocked(false);
        userRepository.saveAndFlush(newUser);
        return userDTO;
    }

    public UserDTO blockUnblockUser(long id) {
        Optional<User> optionalUser = userRepository.findByIdAndDeletedIsFalse(id);
            if (optionalUser.isEmpty()) throw new UserNotFoundException("User not found!");
        User user = optionalUser.get();
        user.setBlocked(!user.getBlocked());
        userRepository.saveAndFlush(user);
        return new UserDTO(user);
    }

    public User findUserById(long id) {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent())return optUser.get();
        throw new UserNotFoundException("User not found!");
    }

}
