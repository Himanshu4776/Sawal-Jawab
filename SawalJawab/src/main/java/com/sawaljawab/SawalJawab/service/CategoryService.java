package com.sawaljawab.SawalJawab.service;

import com.sawaljawab.SawalJawab.Dtos.CategoryDto;
import com.sawaljawab.SawalJawab.Repositories.CategoryRepository;
import com.sawaljawab.SawalJawab.entities.Category;
import com.sawaljawab.SawalJawab.entities.Questions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Category addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return categoryRepository.save(category);
    }

    public Category getCategoryFromName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Questions> getQuestionsFromCategory(String categoryName) {
        Category categoryFromName = getCategoryFromName(categoryName);
        return categoryFromName.getQuestions();
    }
}
