package com.thelma.ecommercewebsite.controller;

import com.thelma.ecommercewebsite.dto.ProductDto;
import com.thelma.ecommercewebsite.exception.CustomAppException;
import com.thelma.ecommercewebsite.model.Product;
import com.thelma.ecommercewebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;

    @GetMapping({"/add_product"})
    public String getCreateProductPage(Model model){
//        model.addAttribute("product", new ProductDto());
        return "add_product";
    }
    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute ProductDto productDto) {
        Product product = productService.addProduct(productDto);
        if(Objects.nonNull(product)) {
            return "redirect:/admin_home";
        } else {
            return "redirect:/add_product";
        }
    }
    @GetMapping({"/edit_product/{id}"})
    public String getEditProductPage(@PathVariable String id, Model model) throws CustomAppException {
       Product product = productService.viewProduct(Long.parseLong(id));
       model.addAttribute("product", product);
       return "edit_product";
    }

    @PostMapping("/edit_product/{id}")
    public String editProduct(@ModelAttribute ProductDto productDto, @PathVariable String id) {
        productService.updateProduct(productDto, Long.parseLong(id));
        return "redirect:/admin_home";
    }
    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(Long.parseLong(id));
        return "redirect:/admin_home";
    }

}
