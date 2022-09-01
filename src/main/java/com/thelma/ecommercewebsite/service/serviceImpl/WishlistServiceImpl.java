package com.thelma.ecommercewebsite.service.serviceImpl;

import com.thelma.ecommercewebsite.dto.WishlistDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Cart;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.model.Wishlist;
import com.thelma.ecommercewebsite.repository.ProductRepository;
import com.thelma.ecommercewebsite.repository.WishlistRepository;
import com.thelma.ecommercewebsite.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final HttpSession httpSession;
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    @Override
    public Wishlist createWishlist(AppUser appUser) {
        Wishlist wishlist = Wishlist.builder()
                .user(appUser)
                .build();
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void addToWishlist(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomAppException("Product doesn't exit."));
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(appUser.getId());
        if (wishlist.isPresent()) {
            List<Product> productList = wishlist.get().getProduct();
            if (!productList.contains(product)) productList.add(product);
            wishlist.get().setProduct(productList);
            wishlistRepository.save(wishlist.get());
        } else {
            Wishlist newCart = createWishlist(appUser);
            List<Product> newProductList = new ArrayList<>();
            newProductList.add(product);
            newCart.setProduct(newProductList);
            wishlistRepository.save(newCart);
        }
    }

    @Override
    public void removeFromWishlist(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomAppException("Product doesn't exit."));
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(appUser.getId());
        if (wishlist.isPresent()) {
            List<Product> productList = wishlist.get().getProduct();
            if (productList.contains(product)) productList.remove(product);
            wishlist.get().setProduct(productList);
            wishlistRepository.save(wishlist.get());
        }
    }

    @Override
    public void addToCart(Long id) throws CustomAppException {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomAppException("Product doesn't exit."));
        Optional<Wishlist> wishlist = wishlistRepository.findById(id);
        //not complete.....

    }

    @Override
    public List<Product> viewProductInWishlist() {
        AppUser appUser = (AppUser) httpSession.getAttribute("loggedInUser");
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(appUser.getId());
        if (wishlist.isPresent()) {
            return wishlist.get().getProduct();
        } else {
            return createWishlist(appUser).getProduct();
        }
    }
}
