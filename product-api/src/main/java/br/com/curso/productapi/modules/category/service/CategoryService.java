package br.com.curso.productapi.modules.category.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.category.dto.CategoryRequest;
import br.com.curso.productapi.modules.category.dto.CategoryResponse;
import br.com.curso.productapi.modules.category.model.Category;
import br.com.curso.productapi.modules.category.repository.CategoryRepository;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Integer id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no category for the given ID..."));
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
