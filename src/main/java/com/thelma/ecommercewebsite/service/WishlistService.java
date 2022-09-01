package com.thelma.ecommercewebsite.service;

import com.thelma.ecommercewebsite.dto.CartDto;
import com.thelma.ecommercewebsite.dto.WishlistDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Cart;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.model.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist createWishlist (AppUser appUser);

    void addToWishlist(Long id) throws CustomAppException;
    void removeFromWishlist(Long id) throws CustomAppException;

    void addToCart(Long id) throws CustomAppException;
    List<Product> viewProductInWishlist();
}
