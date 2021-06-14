package io.swagger.service;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;


    List<User> users;

    public UserService() {

    }

    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> getUserQuery(Long id, String lastname, int limit) {
        List<User> users = new ArrayList<>();
        if (id != null) {
            users.add(this.getById(id));
        } else if (lastname != null) {
            this.getByLastName(lastname);
        } else if (limit != 0) {
            users = (List<User>) userRepository.findAll();
            List<User> newusers = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                newusers.add(users.get(i));
            }
            return newusers;
        }
        return users;
    }

    public String getLogin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtUtil.generateToken(username, userRepository.findByUsername(username).getType());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Invalid username/password");
        }
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByLastName(String lastname) {
        return userRepository.findByLastName(lastname);
    }

    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username).password(user.getPassword()).authorities(user.getType())
                .accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
    }
}
