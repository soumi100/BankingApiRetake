package io.swagger.service;

import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;

    public boolean isEmployee() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUserName(name);
        return user.getType() == User.TypeEnum.EMPLOYEE;
    }

    public User getCurrentUser(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getByUserName(name);
    }
}
