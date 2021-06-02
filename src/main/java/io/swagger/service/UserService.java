package io.swagger.service;

import io.swagger.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

@Service
public class UserService {
    List<User> users;

    public UserService() {
        users = new ArrayList<>();
        User user =new User();
        user.setId(1L);
        user.setActive(true);
        user.setAddress("Kets");
        user.setBirthdate(LocalDate.now());
        user.setPassword("test123");
        user.setEmail("prinsalvino@gmail.com");
        user.setUsername("prinsalvino");
        users.add(user);
    }

    public List<User> getAllUser(){
        return users;
    }
}
