package br.com.curso.productapi.modules.product.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.category.dto.CategoryRequest;
import br.com.curso.productapi.modules.category.dto.CategoryResponse;
import br.com.curso.productapi.modules.category.model.Category;
import br.com.curso.productapi.modules.category.repository.CategoryRepository;
import br.com.curso.productapi.modules.category.service.CategoryService;
import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.product.dto.ProductResponse;
import br.com.curso.productapi.modules.product.model.Product;
import br.com.curso.productapi.modules.product.repository.ProductRepository;
import br.com.curso.productapi.modules.supplier.repository.SupplierRepository;
import br.com.curso.productapi.modules.supplier.service.SupplierService;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {

    private static final Integer ZERO = 0;

    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, SupplierService supplierService,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.supplierService = supplierService;
        this.categoryService = categoryService;
    }

    public ProductResponse save(ProductRequest productRequest) {
        validateProductDataInformed(productRequest);
        validateCategoryAndSupplierIdInformed(productRequest);

        var supplier = supplierService.findById(productRequest.getSupplierId());
        var category = categoryService.findById(productRequest.getCategoryId());
        var product = productRepository.save(Product.of(productRequest, supplier, category));
        return ProductResponse.of(product);
    }

    private void validateProductDataInformed(ProductRequest productRequest) {
        if (isEmpty(productRequest.getName())) {
            throw new ValidationException("The product's name was not informed...");
        }
        if (isEmpty(productRequest.getQuantityAvailable())) {
            throw new ValidationException("The product's quantity was not informed...");
        }
        if (productRequest.getQuantityAvailable() <= ZERO) {
            throw new ValidationException("The quantity should not be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierIdInformed(ProductRequest productRequest) {
        if (isEmpty(productRequest.getCategoryId())) {
            throw new ValidationException("The category ID was not informed...");
        }
        if (isEmpty(productRequest.getSupplierId())) {
            throw new ValidationException("The supplier ID was not informed...");
        }
    }

}
