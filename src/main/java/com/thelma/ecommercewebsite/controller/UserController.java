package com.thelma.ecommercewebsite.controller;

import com.thelma.ecommercewebsite.dto.*;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.service.CartService;
import com.thelma.ecommercewebsite.service.ProductService;
import com.thelma.ecommercewebsite.service.UserService;
import com.thelma.ecommercewebsite.service.WishlistService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {
//    @Autowired
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final WishlistService wishlistService;
    private final HttpSession session;

    @GetMapping("/signup")
    public String signup (){
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView createAccount(@ModelAttribute AppUserDto user){
        AppUser appUser = userService.createUser(user);

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", appUser);
        if(Objects.nonNull(appUser)){
            return mav;
        }else{
            return new ModelAndView("signup");
        }
    }

    @GetMapping({"/login", "/"})
    public ModelAndView getLoginPage(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new AppUser());
        return mav;
    }

    @GetMapping("/home")
    public String  home(Model model) {
        List<Product> productList = productService.displayAllProducts();
        model.addAttribute("productList",productList);
        return "home";
    }
    @GetMapping("/admin_home")
    public String adminHome(Model model) {
        List<Product> productList = productService.displayAllProducts();
        model.addAttribute("productList",productList);
        return "admin_home";
    }

    @PostMapping({"/login", "/"})
    public ModelAndView userLogin(LoginDto loginDto){
        return userService.login(loginDto);
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/add_to_cart/{id}")
    public String addProductToCart(@PathVariable String id) throws CustomAppException {
        cartService.addToCart(Long.parseLong(id));
        return "redirect:/home";
    }
    @GetMapping("/view_cart")
    public String viewUserCart (Model model){
        List<Product> productList = cartService.viewProductInCart();
        model.addAttribute("productList",productList);
        return "view_cart";
    }

    @GetMapping("/remove_from_cart/{id}")
    public String deleteCart(@PathVariable String id) throws CustomAppException{
        cartService.deleteCart(Long.parseLong(id)) ;
        return "redirect:/home";
    }

    @GetMapping("/add_to_wishlist/{id}")
    public String addProductToWishlist(@PathVariable String id) throws CustomAppException {
        wishlistService.addToWishlist(Long.parseLong(id));
        return "redirect:/home";
    }

    @GetMapping("/view_wishlist")
    public String viewUserWishlist (Model model){
        List<Product> productList = wishlistService.viewProductInWishlist();
        model.addAttribute("productList",productList);
        return "view_wishlist";
    }
    @GetMapping("/remove_from_wishlist/{id}")
    public String removeFromWishlist(@PathVariable String id) throws CustomAppException{
        wishlistService.removeFromWishlist(Long.parseLong(id)); ;
        return "redirect:/home";
    }

}
