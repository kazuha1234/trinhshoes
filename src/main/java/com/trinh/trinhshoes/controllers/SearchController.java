package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.models.dto.CategoriesDto;
import com.trinh.trinhshoes.services.CategoryService;
import com.trinh.trinhshoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private ProductService productService;
    private CategoryService categoryService;

    public SearchController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping ("/products")
    public String listProducts(
            Model model,
            @RequestParam(name="pageNo", defaultValue = "1") Integer pageNo,
            @Param("keyword") String keyword) {

        Page<Products> products = productService.findAllProduct(pageNo);
        List<CategoriesDto> categories = categoryService.findAllCategories();

        if(keyword != null) {
            products = this.productService.searchProduct(keyword, pageNo);
        }
        else {
            products = this.productService.findAllProduct(pageNo);
        }

        if(products.isEmpty()) {
            model.addAttribute("message", "Product does not exist");
            model.addAttribute("totalPage", 1);
            model.addAttribute("currentPage", 1);
        }

        model.addAttribute("products", products);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);

        return "category";
    }
}
