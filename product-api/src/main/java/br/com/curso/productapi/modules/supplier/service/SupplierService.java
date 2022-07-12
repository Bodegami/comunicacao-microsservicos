package br.com.curso.productapi.modules.supplier.service;

import br.com.curso.productapi.config.exception.SuccessResponse;
import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.product.service.ProductService;
import br.com.curso.productapi.modules.supplier.dto.SupplierRequest;
import br.com.curso.productapi.modules.supplier.dto.SupplierResponse;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import br.com.curso.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    private final ProductService productService;

    public SupplierService(SupplierRepository supplierRepository, ProductService productService) {
        this.supplierRepository = supplierRepository;
        this.productService = productService;
    }

    public List<SupplierResponse> findByAll() {
        return supplierRepository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException(("The supplier name must be informed..."));
        }
        return supplierRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
    }

    public Supplier findById(Integer id) {
        validateInformedId(id);
        return supplierRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no supplier for the given ID..."));
    }

    public SupplierResponse save(SupplierRequest supplierRequest) {
        validateSupplierNameInformed(supplierRequest);
        var supplier = supplierRepository.save(Supplier.of(supplierRequest));
        return SupplierResponse.of(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequest supplierRequest) {
        if (isEmpty(supplierRequest.getName())) {
            throw new ValidationException("The supplier's name was not informed...");
        }
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        if (productService.existsBySupplierId(id)) {
            throw new ValidationException("you cannot delete this supplier because it's already defined by a product.");
        }
        supplierRepository.deleteById(id);
        return SuccessResponse.create("The supplier was deleted.");
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException(("The supplier ID was not informed..."));
        }
    }

}
