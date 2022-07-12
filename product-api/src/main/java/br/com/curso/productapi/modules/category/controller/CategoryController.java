package br.com.curso.productapi.modules.category.controller;

import br.com.curso.productapi.config.exception.SuccessResponse;
import br.com.curso.productapi.modules.category.dto.CategoryRequest;
import br.com.curso.productapi.modules.category.dto.CategoryResponse;
import br.com.curso.productapi.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findByAll();
    }

    @GetMapping("/description/{description}")
    public List<CategoryResponse> findByDescription(@PathVariable(name = "description") String categoryDescription) {
        return categoryService.findByDescription(categoryDescription);
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable(name = "id") Integer categoryId) {
        return categoryService.findByIdResponse(categoryId);
    }

    @PutMapping("{id}")
    public CategoryResponse update(@RequestBody CategoryRequest request,
                                   @PathVariable Integer id) {
        return categoryService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
