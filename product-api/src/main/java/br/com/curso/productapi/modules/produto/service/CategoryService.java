package br.com.curso.productapi.modules.produto.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.produto.dto.CategoryRequest;
import br.com.curso.productapi.modules.produto.dto.CategoryResponse;
import br.com.curso.productapi.modules.produto.model.Category;
import br.com.curso.productapi.modules.produto.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        validateCategoryNameInformed(categoryRequest);
        var category = categoryRepository.save(Category.of(categoryRequest));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequest categoryRequest) {
        if (isEmpty(categoryRequest.getDescription())) {
            throw new ValidationException("The category description was not informed...");
        }
    }

}