package com.sawaljawab.SawalJawab.Repositories;

import com.sawaljawab.SawalJawab.Repositories.CustomRepository.CustomCategoryRepository;
import com.sawaljawab.SawalJawab.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CustomCategoryRepository {
}

