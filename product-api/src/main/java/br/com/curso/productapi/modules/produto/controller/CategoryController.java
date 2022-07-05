package br.com.curso.productapi.modules.produto.controller;

import br.com.curso.productapi.modules.produto.dto.CategoryRequest;
import br.com.curso.productapi.modules.produto.dto.CategoryResponse;
import br.com.curso.productapi.modules.produto.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }


}
