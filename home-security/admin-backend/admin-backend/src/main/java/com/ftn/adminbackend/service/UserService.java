package com.ftn.adminbackend.service;

import java.util.List;
import java.util.Optional;

import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.UserRepository;

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

    // public void updateUser(UserDTO userDTO) {
    //     Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    //     String username = currentUser.getName();
    //     User user = (User) findByUsername(username);

    //     user.setFirstName(userDTO.getFirstName());
	// 	user.setLastName(userDTO.getLastName());
	// 	user.setPhoneNumber(userDTO.getPhoneNumber());
	// 	user.setEmail(userDTO.getEmail());
    //     userRepository.save(user);
    // }

    // public String register(NewUserDTO userDTO, String siteURL) throws MessagingException, UnsupportedEncodingException {

    //     List<User> sameUsernameOrEmailUsers = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        
    //     if(sameUsernameOrEmailUsers.size()!=0){
    //         return "User with same username/email exists!";
    //     }

    //     Patient user = new Patient(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getPhoneNumber());
        
	// 	Address userAddress = new Address(userDTO.getStreet(), userDTO.getCity(), "", "", userDTO.getCountry());

	// 	addressRepository.saveAndFlush(userAddress);

	// 	user.setAddress(userAddress);
		
	// 	String randomCode = RandomString.make(64);
    //     VerificationCode verificationCode = new VerificationCode(randomCode, user, false, LocalDate.now());
    //     verificationCodeRepository.save(verificationCode);
    //     user.setVerificationCode(verificationCode);
        		
	// 	userRepository.saveAndFlush(user);
    //     verificationCodeRepository.saveAndFlush(verificationCode);
	// 	sendVerificationEmail(user, siteURL);
    //     return "Verification link has been sent to your email!";
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findAdminByUsernameAndDeletedIsFalseAndRole(username, "ADMIN");
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("No such user exists");
    }


}
