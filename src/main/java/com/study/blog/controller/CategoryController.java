package com.study.blog.controller;


import com.study.blog.domain.dtos.CategoryDto;
import com.study.blog.domain.entity.Category;
import com.study.blog.mappers.CategoryMapper;
import com.study.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor //create a constructor which has instance with final and will be injected

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){

        List<Category> categories = categoryService.listCategories();

        return ResponseEntity.ok(categories.stream().map(category -> categoryMapper.toDto(category)).toList());

    }
}
