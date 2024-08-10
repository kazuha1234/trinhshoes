package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.models.dto.CategoriesDto;
import com.trinh.trinhshoes.services.CategoryService;
import com.trinh.trinhshoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;


    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home/products")
    public String listProducts(
            Model model,
            @RequestParam(name="pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "filter", defaultValue = "1", required = false) String filter) {

            Page<Products> products = productService.findAllProduct(pageNo);
            List<CategoriesDto> categories = categoryService.findAllCategories();

            if(filter != null && !filter.isEmpty()) {
                if(filter.equals("1")) {
                    products = productService.findAllProduct(pageNo);
                }
                else if(filter.equals("2")) {
                    products = productService.searchProductsByAsc(products, pageNo);
                }
                else {
                    products = productService.searchProductsByDesc(products, pageNo);
                }
            }

            model.addAttribute("products", products);
            model.addAttribute("totalPage", products.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("categories", categories);

        return "category";
    }

    @GetMapping("/products/categories")
    public String listProductsByCategory(
            Model model,
            @RequestParam(name="cate") String categoryId,
            @RequestParam(value = "filter", defaultValue = "1", required = false) String filter,
            @RequestParam(name="pageNo", defaultValue = "1") Integer pageNo) {

        Categories cate = new Categories();
        cate.setCategoryId(categoryId);
        Page<Products> products = productService.searchProductsByCategoryId(cate, pageNo);
        List<CategoriesDto> categories = categoryService.findAllCategories();

        if(filter != null && !filter.isEmpty()) {
            if(filter.equals("1")) {
                products = productService.searchProductsByCategoryId(cate, pageNo);
            }
            else if(filter.equals("2")) {
                products = productService.searchProductsByAsc(products, pageNo);
            }
            else {
                products = productService.searchProductsByDesc(products, pageNo);
            }
        }

        model.addAttribute("categories2", categories);
        model.addAttribute("products2", products);
        model.addAttribute("cate", categoryId);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        return "category2";
    }
}
