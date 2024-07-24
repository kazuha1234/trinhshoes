package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.models.dto.CategoriesDto;
import com.trinh.trinhshoes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {

    public CategoryController() {}
    @Autowired
    public CategoryController(CategoryService categoryService) {
    }

//    @RequestMapping("/home/products")
//    public String findAllCategories(Model model) {
//        List<CategoriesDto> categories = categoryService.findAllCategories();
//        model.addAttribute("categories", categories);
//
//        return "category";
//    }
}
