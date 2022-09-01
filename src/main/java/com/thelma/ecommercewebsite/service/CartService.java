package com.thelma.ecommercewebsite.service;

import com.thelma.ecommercewebsite.dto.CartDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Cart;
import com.thelma.ecommercewebsite.model.Product;

import java.util.List;

public interface CartService {
    Cart createCart (AppUser appUser);

    void addToCart(Long id) throws CustomAppException;
    void deleteCart(Long id) throws CustomAppException;
    void updateCart(CartDto cartDto, Long id);
    List<Product> viewProductInCart();

}
