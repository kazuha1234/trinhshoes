package com.trinh.trinhshoes.repository;

import com.trinh.trinhshoes.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, String> {
}
