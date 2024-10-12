package com.sawaljawab.SawalJawab.controllers;

import com.sawaljawab.SawalJawab.Repositories.CategoryRepository;
import com.sawaljawab.SawalJawab.entities.Category;
import com.sawaljawab.SawalJawab.entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public Category createCategory(@RequestBody Category categoryToAdd) {
        return categoryRepository.save(categoryToAdd);
    }

    @GetMapping("/{categoryName}")
    public Category getMatchingCategory(@PathVariable String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @GetMapping("/{categoryName}/getQuestions")
    public List<Questions> getQuestionsByCategory(@PathVariable String categoryName) {
        Category byCategoryName = categoryRepository.findByCategoryName(categoryName);
        return byCategoryName.getQuestions();
    }
}
