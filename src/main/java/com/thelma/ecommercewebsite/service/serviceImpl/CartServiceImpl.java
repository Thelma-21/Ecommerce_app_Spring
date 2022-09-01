package com.thelma.ecommercewebsite.service.serviceImpl;

import com.thelma.ecommercewebsite.dto.CartDto;
import com.thelma.ecommercewebsite.dto.ProductDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Cart;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.repository.CartRepository;
import com.thelma.ecommercewebsite.repository.ProductRepository;
import com.thelma.ecommercewebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final HttpSession httpSession;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart createCart(AppUser appUser) {
        Cart cart = Cart.builder()
                .user(appUser)
                .build();
        return cartRepository.save(cart);
    }

    @Override
    public void addToCart(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomAppException("Product doesn't exit."));
        Optional<Cart> cart = cartRepository.findByUserId(appUser.getId());
        if (cart.isPresent()) {
            List<Product> productList = cart.get().getProduct();
            if (!productList.contains(product)) productList.add(product);
            cart.get().setProduct(productList);
            cartRepository.save(cart.get());
        } else {
            Cart newCart = createCart(appUser);
            List<Product> newProductList = new ArrayList<>();
            newProductList.add(product);
            newCart.setProduct(newProductList);
            cartRepository.save(newCart);
        }
    }

    @Override
    public void updateCart(CartDto cartDto, Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            Cart udCart = cart.get();
            udCart.setProduct(cartDto.getProduct());
            udCart.setUser(cartDto.getUser());
            cartRepository.save(udCart);

        } else {
            System.out.println("cart not found");
            //todo use custom exception
        }
    }

    @Override
    public List<Product> viewProductInCart() {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Optional<Cart> cart = cartRepository.findByUserId(appUser.getId());
        if (cart.isPresent()) {
            return cart.get().getProduct();
        } else {
            return createCart(appUser).getProduct();
        }

    }

    @Override
    public void deleteCart(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomAppException("Product doesn't exit."));
        Optional<Cart> cart = cartRepository.findByUserId(appUser.getId());
        if (cart.isPresent()) {
            List<Product> productList = cart.get().getProduct();
            if (productList.contains(product)) productList.remove(product);
            cart.get().setProduct(productList);
            cartRepository.save(cart.get());
        }
    }
}
