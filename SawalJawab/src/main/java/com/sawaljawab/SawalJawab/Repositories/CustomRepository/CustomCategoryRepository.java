package com.sawaljawab.SawalJawab.Repositories.CustomRepository;

import com.sawaljawab.SawalJawab.entities.Category;

public interface CustomCategoryRepository {
    public Category findByCategoryName(String categoryName);
}
