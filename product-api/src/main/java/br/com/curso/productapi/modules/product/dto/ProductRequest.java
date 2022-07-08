package br.com.curso.productapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    @JsonProperty("quantity_available")
    private Integer quantityAvailable;

    @JsonProperty("supplier_id")
    private Integer supplierId;

    @JsonProperty("category_id")
    private Integer categoryId;
}
