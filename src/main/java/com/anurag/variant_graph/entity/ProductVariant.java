package com.anurag.variant_graph.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String variantName;  // e.g. "Black Mesh", "Walnut 60inch"

    private String colour;
    private String size;
    private BigDecimal price;

    @Column(nullable = false)
    private Long baseProductId;  // points to product in CatalogCache
    // plain Long — no @ManyToOne, Product lives in a separate service
}
