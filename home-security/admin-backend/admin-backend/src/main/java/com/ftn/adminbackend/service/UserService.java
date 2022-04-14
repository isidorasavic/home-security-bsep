// package com.ftn.adminbackend.service;

// import java.util.Optional;

// import com.ftn.adminbackend.model.User;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService implements UserDetailsService{

//     private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

// 	// @Autowired
// 	// private UserRepository userRepository;

// 	@Autowired
// 	private PasswordEncoder passwordEncoder;

// 	@Autowired
// 	private AuthenticationManager authenticationManager;

// 	private User user;


// 	public UserService() {
// 	}


// 	public UserService(User user) {
//         this.user = user;
//     }
    
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<User> maybeUser = Optional.of(new User("isidora", "savic", "admin"));//userRepository.findByUsername(username);
// 		if (maybeUser.isPresent()) {
// 			return maybeUser.get();
// 		} else {
// 			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
// 		}
//     }

// 	// public boolean changePassword(PasswordDTO passwordDTO) {

// 	// 	// Ocitavamo trenutno ulogovanog korisnika
// 	// 	Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
// 	// 	String username = currentUser.getName();
		
// 	// 	if (authenticationManager != null) {
// 	// 		LOG.info("Re-authenticating user '" + username + "' for password change request.");
// 	// 		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, passwordDTO.getOldPassword()));
// 	// 	} else {
// 	// 		LOG.info("No authentication manager set. can't change Password!");
// 	// 		return false;
// 	// 	}

// 	// 	LOG.info("Changing password of the user '" + username + "'");

// 	// 	User user = (User) loadUserByUsername(username);

// 	// 	// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
// 	// 	// ne zelimo da u bazi cuvamo lozinke u plain text formatu
// 	// 	user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
// 	// 	if(user.isNeedsToChangePassword()) user.setNeedsToChangePassword(false);
// 	// 	userRepository.save(user);
// 	// 	return true;
// 	// }

//     // public boolean isEnabled() {
//     //     return user.isEnabled();
//     // }
    
// }
