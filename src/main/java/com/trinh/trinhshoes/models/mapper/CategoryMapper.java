package com.trinh.trinhshoes.models.mapper;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.models.dto.CategoriesDto;

public class CategoryMapper {
    public static CategoriesDto mapToCategoriesDto(Categories categories) {
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setCategoryId(categories.getCategoryId());
        categoriesDto.setCategoryName(categories.getCategoryName());

        return categoriesDto;
    }
}
