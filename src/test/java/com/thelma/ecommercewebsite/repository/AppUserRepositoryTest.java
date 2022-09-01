package com.thelma.ecommercewebsite.repository;

import com.thelma.ecommercewebsite.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository underTest;

    @Test
    void itShouldCheckIfAppUserExistsByEmail() {
        //given
        String email = "tee@gmail.com";
        AppUser appUser = new AppUser(
                1L,
                "Thelma",
                email,
                "tochi"
        );
        underTest.save(appUser);

        //when
        Optional<AppUser> expected = underTest.findAppUserByEmail(email);

        //then
        assertThat(expected).isPresent();
    }

    @Test
    void itShouldCheckIfAppUserExistsByEmailAndPassword() {
        //given
        String email = "kay@gmail.com";
        String password = "caleb";
        AppUser appUser = new AppUser(
                2L,
                "Kay",
                email,
                password

        );
        underTest.save(appUser);
        //when
        Optional<AppUser> expected = underTest.findAppUserByEmailAndPassword(email, password);

        //then
        assertThat(expected).isPresent();
    }
}