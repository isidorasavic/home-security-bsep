package com.ftn.adminbackend.service;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.exception.RoleNotFound;
import com.ftn.adminbackend.exception.UserExistsException;
import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.UserRepository;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



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

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public static String BASE_URL = "http://localhost:8081/api";

	public static String URL_ENCODING = "UTF-8";

    public UserDTO addUser(UserDTO userDTO) throws Exception{
        
        if(userRepository.findAdminByUsernameAndDeletedIsFalse(userDTO.getUsername()).isPresent()){
            throw new UserExistsException("Username is taken!");
        }

        if(!userDTO.getRole().equals("OWNER") && !userDTO.getRole().equalsIgnoreCase("TENANT")&& !userDTO.getRole().equalsIgnoreCase("ADMIN")){
            throw new RoleNotFound("Role not found!");
        }

        User newUser = new User(userDTO);
        newUser.setId(userRepository.findAll().size()+1);
        userRepository.save(newUser);

        //koji god korisnik da se doda, on ce se slati i na MyHouse bek da bi tamo mogao da se uloguuje
        //ako se doda novi korisnik u MyHouse, on ce biti poslat ovde da bi admin mogao da ga vidi (ovo vrv ne treba ali nema veze)
        String url = BASE_URL + "/user/addUser";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI uri = new URI(url);

        HttpEntity<UserDTO> httpEntity = new HttpEntity<>(userDTO, headers);

        RestTemplate restTemplate = new RestTemplate();
        UserDTO userDTO2 = restTemplate.postForObject(uri, httpEntity, UserDTO.class);
        LOG.info("Finished comunicating with MyHouse. Response: " + userDTO2);
        return userDTO;

    }

    public static String getStringFromInputStream(InputStream in) throws Exception {
		return new String(IOUtils.toByteArray(in), URL_ENCODING);
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findAdminByUsernameAndDeletedIsFalseAndRole(username, "ADMIN");
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("No such user exists");
    }


}
