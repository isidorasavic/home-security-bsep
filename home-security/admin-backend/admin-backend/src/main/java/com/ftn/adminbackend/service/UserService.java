package com.ftn.adminbackend.service;

import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.exception.*;
import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.model.enums.UserRole;
import com.ftn.adminbackend.repository.ObjectRepository;
import com.ftn.adminbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;

    private final ObjectRepository objectRepository;


    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, ObjectRepository objectRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
    }

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

    public UserDTO changeRole(long id,String newRole){
        UserRole role;
        try{
            role = UserRole.valueOf(newRole);
        }
        catch (Exception e){
            throw new RoleNotFound("Role not found!");
        }

        Optional<User> userOptional = userRepository.findByIdAndDeletedIsFalse(id);
        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        User user = userOptional.get();

        if(user.getRole() == UserRole.ADMIN) throw new InvalidArgumentException("Admin can't change role!");

        if (objectRepository.findAllByOwnerId(id).size()!= 0 && user.getRole() == UserRole.OWNER && role==UserRole.TENANT) {
            throw new UserContainsObjectsException("User owns objects and can't be turned to tennant!");
        }

        user.setRole(role);
        userRepository.saveAndFlush(user);
        return new UserDTO(user);
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
