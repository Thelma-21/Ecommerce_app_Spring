package com.thelma.ecommercewebsite.service;

import com.thelma.ecommercewebsite.dto.AppUserDto;
import com.thelma.ecommercewebsite.dto.LoginDto;
import com.thelma.ecommercewebsite.model.AppUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

public interface UserService {
    AppUser createUser(AppUserDto appUserDto);
    ModelAndView login(LoginDto loginDto);
}
