package com.trinh.trinhshoes.services.imp;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.models.dto.CategoriesDto;
import com.trinh.trinhshoes.models.mapper.CategoryMapper;
import com.trinh.trinhshoes.models.mapper.ProductMapper;
import com.trinh.trinhshoes.repository.CategoryRepository;
import com.trinh.trinhshoes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoriesDto> findAllCategories() {
        List<Categories> categories = categoryRepository.findAll();
        List<CategoriesDto> result = new ArrayList<>();
        for(Categories category : categories) {
            result.add(CategoryMapper.mapToCategoriesDto(category));
        }

        return result;
    }
}
