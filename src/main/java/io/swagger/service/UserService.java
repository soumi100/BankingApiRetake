package io.swagger.service;

import io.swagger.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

@Service
public class UserService implements UserDetailsService {
    List<User> users;

    public UserService() {
        users = new ArrayList<>();
        User user =new User();
        user.setId(1L);
        user.setActive(true);
        user.setUsername("prinsalvino");
        user.setPassword("test123");
        user.setFirstName("Prins");
        user.lastName("Alvino");
        user.setEmail("prinsalvino@gmail.com");
        user.setBirthdate(LocalDate.now());

        user.setAddress("Kets");
        user.setPostalcode("1156AX");
        user.setCity("Marken");
        user.setPhoneNumber("0855");
        user.setType(User.TypeEnum.EMPLOYEE);
        users.add(user);
    }

    public List<User> getAllUser(){
        return users;
    }

    public User getLogin(String username, String password){
        for (User user:users) {
            System.out.println(user.getUsername());
            System.out.println(username);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (User user:users) {
            if (user.getUsername().equals(username)){
                return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
            }
        }
        return null;
    }
}
