package com.example.assignment01.utils;

import com.example.assignment01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ContextUtils {

   @Autowired
   private  IUserService userService;

    public  UserDetails getUserLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals("anonymousUser")){
            return null;
        }else {
            User userLogin = (User) authentication.getPrincipal();
            UserDetails userLoginDTO = userService.loadUserByUsername(userLogin.getUsername()) ;
            return userLoginDTO;
        }

    }
}
