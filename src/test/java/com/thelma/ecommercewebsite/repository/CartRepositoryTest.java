package com.thelma.ecommercewebsite.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CartRepositoryTest {
    @Autowired
    private CartRepository underTest;

    @Test
    void findByUserId() {
        //given

        //when
        //then
    }
}