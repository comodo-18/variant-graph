package com.anurag.variant_graph.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variant")
@Data
@NoArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colour;
    private String size;
    private BigDecimal price;
    private Long baseProductId;  // points to product in CatalogCache
    // just an ID — no @ManyToOne needed
    // because Product lives in another service
}
