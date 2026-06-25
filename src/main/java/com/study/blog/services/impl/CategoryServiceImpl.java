package com.study.blog.services.impl;

import com.study.blog.domain.entity.Category;
import com.study.blog.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> listCategories() {
        return List.of();
    }
}
