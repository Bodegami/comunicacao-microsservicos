package br.com.curso.productapi.modules.produto.repository;

import br.com.curso.productapi.modules.produto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
