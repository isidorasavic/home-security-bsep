package com.ftn.adminbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.bytebuddy.utility.RandomString;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.model.UserTokenState;
import com.ftn.adminbackend.security.TokenUtils;
import com.ftn.adminbackend.security.authentication.JwtAuthenticationRequest;
import com.ftn.adminbackend.service.CustomUserDetailsService;
import com.ftn.adminbackend.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TokenUtils tokenUtils;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    //log in endpoint
    @PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		LOG.info("Received request for login");
        LOG.info("username: "+authenticationRequest.getUsername());
        LOG.info("password: "+authenticationRequest.getPassword());

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));


		LOG.info("Authentication passed");

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
        String fingerprint = tokenUtils.generateFingerprint();
        String jwt = tokenUtils.generateToken(user.getUsername(), fingerprint);
        int expiresIn = tokenUtils.getExpiredIn();

        // Kreiraj cookie
        // String cookie = "__Secure-Fgp=" + fingerprint + "; SameSite=Strict; HttpOnly; Path=/; Secure";  // kasnije mozete probati da postavite i ostale atribute, ali tek nakon sto podesite https
        String cookie = "Fingerprint=" + fingerprint + "; HttpOnly; Path=/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookie);

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok().headers(headers).body(new UserTokenState(jwt, expiresIn));
	}


	@PostMapping("/logout")
	public void logout(HttpServletResponse response) {
		// create a cookie
		Cookie cookie = new Cookie("accessToken", null);
		cookie.setMaxAge(0);
		// cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");

		//add cookie to response
		response.addCookie(cookie);
	}

    // //refresh endpoint
    // @PostMapping(value = "/refresh")
	// public void refreshAuthenticationToken(HttpServletRequest request) {

	// 	String token = tokenUtils.getToken(request);
	// 	String username = this.tokenUtils.getUsernameFromToken(token);
	// 	User user = (User) this.userDetailsService.loadUserByUsername(username);

	// 	if (this.tokenUtils.canTokenBeRefreshed(token)) {
	// 		String jwt = tokenUtils.refreshToken(token);
	// 		Cookie cookie = new Cookie("accessToken", jwt);
	// 		cookie.setMaxAge(7 * 24 * 60 * 60); // Expires in 7 days
	// 		// cookie.setSecure(true);
	// 		cookie.setHttpOnly(true);
	// 		cookie.setPath("/"); // Global cookie accessible everywhere
	// 	}
	// }

    // @PreAuthorize("hasRole('USER')")
    // @PutMapping(path = "/change-password")
    // public ResponseEntity<PasswordDTO> changePassword(@RequestBody PasswordDTO passwordDTO) {
	// 	if(userDetailsService.changePassword(passwordDTO))
    //     	return ResponseEntity.accepted().body(passwordDTO);
	// 	else
	// 		return ResponseEntity.badRequest().body(passwordDTO);
    // }

    // @PostMapping(value="/registration")
    // public String registration(@RequestBody NewUserDTO userDTO, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
    //     LOG.info("Registration");
	// 	String siteURL = request.getRequestURL().toString();
    //     return userService.register(userDTO, siteURL);       

    // }
    
}
