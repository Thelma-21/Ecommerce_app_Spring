package com.thelma.ecommercewebsite.service;

import com.thelma.ecommercewebsite.dto.ProductDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto productDto);
    void updateProduct(ProductDto productDto, Long id);
    void deleteProduct(Long id);
    List<Product> displayAllProducts();
    Product viewProduct(Long id) throws CustomAppException;
}
