package br.com.curso.productapi.modules.product.model;

import br.com.curso.productapi.modules.category.model.Category;
import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.supplier.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITYAVAILABLE", nullable = false)
    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "FK_SUPPLIER", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", nullable = false)
    private Category category;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Product of(ProductRequest productRequest, Supplier supplier, Category category) {
        return Product.builder()
                .name(productRequest.getName())
                .quantityAvailable(productRequest.getQuantityAvailable())
                .supplier(supplier)
                .category(category)
                .build();
    }

}
