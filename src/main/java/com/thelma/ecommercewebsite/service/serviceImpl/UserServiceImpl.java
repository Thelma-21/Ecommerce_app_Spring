package com.thelma.ecommercewebsite.service.serviceImpl;

import com.thelma.ecommercewebsite.dto.AppUserDto;
import com.thelma.ecommercewebsite.dto.LoginDto;
import com.thelma.ecommercewebsite.model.AppUser;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.repository.AppUserRepository;
import com.thelma.ecommercewebsite.service.ProductService;
import com.thelma.ecommercewebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
//    @Autowired
    private final AppUserRepository appUserRepository;
//    @Autowired
    private final HttpSession session;
    private final ProductService productService;

    @Override
    public AppUser createUser(AppUserDto appUserDto) {
        Optional<AppUser> user = appUserRepository.findAppUserByEmail(appUserDto.getEmail());
        if(user.isPresent()){
            throw new RuntimeException("Account already exist");
        }
        AppUser appUser = AppUser.builder()
                .fullName(appUserDto.getFullName())
                .password(appUserDto.getPassword())
                .email(appUserDto.getEmail())
                .build();
        return appUserRepository.save(appUser);
    }

    @Override
    public ModelAndView login(LoginDto loginDto) {
        if(loginDto.getEmail().equals("admin@gmail.com") && loginDto.getPassword().equals("1234")){
            ModelAndView mav = new ModelAndView("admin_home");
            List<Product> productList = productService.displayAllProducts();
            mav.addObject("productList",productList);
            return mav;
        }

        Optional<AppUser> user = appUserRepository.findAppUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (user.isPresent()){
            session.setAttribute("loggedInUser", user.get());
            List<Product> productList = productService.displayAllProducts();
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("productList",productList);
            mav.addObject("user", user);
            return mav;
        }else {
            return new ModelAndView("login");
        }

    }
}
