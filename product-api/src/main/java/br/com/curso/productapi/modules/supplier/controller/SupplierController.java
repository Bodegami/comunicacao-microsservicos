package br.com.curso.productapi.modules.supplier.controller;

import br.com.curso.productapi.config.exception.SuccessResponse;
import br.com.curso.productapi.modules.supplier.dto.SupplierRequest;
import br.com.curso.productapi.modules.supplier.dto.SupplierResponse;
import br.com.curso.productapi.modules.supplier.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest supplierRequest) {
        return supplierService.save(supplierRequest);
    }

    @GetMapping
    public List<SupplierResponse> findAll() {
        return supplierService.findByAll();
    }

    @GetMapping("/name/{name}")
    public List<SupplierResponse> findByName(@PathVariable(name = "name") String supplierName) {
        return supplierService.findByName(supplierName);
    }

    @GetMapping("/{id}")
    public SupplierResponse findById(@PathVariable(name = "id") Integer supplierId) {
        return supplierService.findByIdResponse(supplierId);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse delete(@PathVariable(name = "id") Integer supplierId) {
        return supplierService.delete(supplierId);
    }

}
