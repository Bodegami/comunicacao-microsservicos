package br.com.curso.productapi.modules.produto.repository;

import br.com.curso.productapi.modules.produto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
