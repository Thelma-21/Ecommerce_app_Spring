package com.thelma.ecommercewebsite.dto;

import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Product;
import lombok.Data;

import java.util.List;
@Data
public class WishlistDto {
    private AppUser user;
    private List<Product> product;
}
