package br.com.curso.productapi.modules.product.controller;

import br.com.curso.productapi.config.exception.SuccessResponse;
import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.product.dto.ProductResponse;
import br.com.curso.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findByAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable(name = "id") Integer productId) {
        return productService.findByIdResponse(productId);
    }

    @GetMapping("/name/{name}")
    public List<ProductResponse> findByName(@PathVariable(name = "name") String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId) {
        return productService.findBySupplierId(supplierId);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable(name = "id") Integer productId,
                                   @RequestBody ProductRequest productRequest) {
        return productService.update(productRequest, productId);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse delete(@PathVariable(name = "id") Integer productId) {
        return productService.delete(productId);
    }

}
