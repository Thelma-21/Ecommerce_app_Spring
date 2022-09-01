package com.thelma.ecommercewebsite.service.serviceImpl;

import com.thelma.ecommercewebsite.dto.ProductDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.repository.ProductRepository;
import com.thelma.ecommercewebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .productName(productDto.getProductName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .build();
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDto productDto, Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product dbProduct = product.get();
            dbProduct.setProductName(productDto.getProductName());
            dbProduct.setCategory(productDto.getCategory());
            dbProduct.setPrice(productDto.getPrice());
            productRepository.save(dbProduct);
        }else{
            System.out.println("product not found");
            //todo use custom exception
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product dbProduct = product.get();
            productRepository.delete(dbProduct);
        }else{
            System.out.println("product not found");
            //todo use custom exception
        }
    }

    @Override
    public List<Product> displayAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product viewProduct(Long id) throws CustomAppException {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("Product does not exist"));
    }
}
