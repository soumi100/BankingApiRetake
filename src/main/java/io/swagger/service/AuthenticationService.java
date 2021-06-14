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
//
//    public User getCurrentUser(){
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (obj instanceof SecurityUserDetails){
//            return ((SecurityUserDetails) obj).getUser();
//        } else {
//            return null;
//        }
//    }
}
