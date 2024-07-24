package com.trinh.trinhshoes.services;

import com.trinh.trinhshoes.models.dto.CategoriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoriesDto> findAllCategories();
}
