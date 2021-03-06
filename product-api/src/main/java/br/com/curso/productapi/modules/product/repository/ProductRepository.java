package br.com.curso.productapi.modules.product.repository;

import br.com.curso.productapi.modules.product.model.Product;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findBySupplierId(Integer supplierId);

    Boolean existsByCategoryId(Integer CategoryId);

    Boolean existsBySupplierId(Integer SupplierId);

}
