package br.com.curso.productapi.modules.product.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.category.service.CategoryService;
import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.product.dto.ProductResponse;
import br.com.curso.productapi.modules.product.model.Product;
import br.com.curso.productapi.modules.product.repository.ProductRepository;
import br.com.curso.productapi.modules.supplier.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductResponse> findByAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException(("The product name must be informed..."));
        }
        return productRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findBySupplierId(Integer supplierId) {
        if (isEmpty(supplierId)) {
            throw new ValidationException(("The product's supplier ID must be informed..."));
        }
        return productRepository
                .findBySupplierId(supplierId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        if (isEmpty(categoryId)) {
            throw new ValidationException(("The product's category ID must be informed..."));
        }
        return productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public ProductResponse findByIdResponse(Integer id) {
        return ProductResponse.of(findById(id));
    }

    public Product findById(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException(("The product ID was not informed..."));
        }

        return productRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no product for the given ID..."));
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
