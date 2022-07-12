package br.com.curso.productapi.modules.category.service;

import br.com.curso.productapi.config.exception.SuccessResponse;
import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.category.dto.CategoryRequest;
import br.com.curso.productapi.modules.category.dto.CategoryResponse;
import br.com.curso.productapi.modules.category.model.Category;
import br.com.curso.productapi.modules.category.repository.CategoryRepository;
import br.com.curso.productapi.modules.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public List<CategoryResponse> findByAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> findByDescription(String description) {
        if (isEmpty(description)) {
            throw new ValidationException(("The category description must be informed..."));
        }
        return categoryRepository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }

    public Category findById(Integer id) {
        validateInformedId(id);
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

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        if (productService.existsByCategoryId(id)) {
            throw new ValidationException("you cannot delete this category because it's already defined by a product.");
        }
        categoryRepository.deleteById(id);
        return SuccessResponse.create("The category was deleted.");
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException(("The category ID was not informed..."));
        }
    }

}
