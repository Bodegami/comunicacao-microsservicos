package br.com.curso.productapi.modules.supplier.model;

import br.com.curso.productapi.modules.category.model.Category;
import br.com.curso.productapi.modules.supplier.dto.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    public static Supplier of(SupplierRequest supplierRequest) {
        var supplier = new Supplier();
        BeanUtils.copyProperties(supplierRequest, supplier);
        return supplier;
    }

}
