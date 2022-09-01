package com.thelma.ecommercewebsite.service;

import com.thelma.ecommercewebsite.dto.AppUserDto;
import com.thelma.ecommercewebsite.dto.LoginDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.repository.AppUserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    @DisplayName("Should create a user when the email is not taken")
    void createUserWhenEmailIsNotTaken() {
        AppUserDto appUserDto =
                AppUserDto.builder()
                        .fullName("Thelma")
                        .email("tee@gmail.com")
                        .password("tochi")
                        .build();
        AppUser appUser = userService.createUser(appUserDto);
        assertEquals(appUser.getFullName(), appUserDto.getFullName());
        assertEquals(appUser.getEmail(), appUserDto.getEmail());
        assertEquals(appUser.getPassword(), appUserDto.getPassword());
    }

    @Test
    @DisplayName("Should throw an exception when the email is already taken")
    void createUserWhenEmailIsAlreadyTakenThenThrowException() {
        AppUserDto appUserDto =
                AppUserDto.builder()
                        .fullName("Thelma")
                        .email("tee@gmail.com")
                        .password("tochi")
                        .build();

        assertThrows(CustomAppException.class, () -> userService.createUser(appUserDto));
    }

    @Test
    @Disabled
    void login() {

    }


}