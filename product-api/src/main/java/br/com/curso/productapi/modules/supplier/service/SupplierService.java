package br.com.curso.productapi.modules.supplier.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.supplier.dto.SupplierRequest;
import br.com.curso.productapi.modules.supplier.dto.SupplierResponse;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import br.com.curso.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier findById(Integer id) {
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

}
