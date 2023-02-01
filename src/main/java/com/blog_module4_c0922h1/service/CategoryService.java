package com.blog_module4_c0922h1.service;

import com.blog_module4_c0922h1.model.Blog;
import com.blog_module4_c0922h1.model.Category;
import com.blog_module4_c0922h1.repository.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategory iCategory;

    public void save(Category category) {
        iCategory.save(category);
    }

    public Category findById(int id) {
        return iCategory.findById(id).get();
    }

    public List<Category> getAll() {
        return (List<Category>) iCategory.findAll();
    }


}

