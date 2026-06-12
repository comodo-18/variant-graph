package com.anurag.variant_graph.service;

import com.anurag.variant_graph.entity.ProductVariant;
import com.anurag.variant_graph.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantService {

    private final ProductVariantRepository variantRepository;

    public ProductVariant createVariant(ProductVariant variant) {
        return variantRepository.save(variant);
    }

    public List<ProductVariant> getVariantsByProductId(Long productId) {
        return variantRepository.findByBaseProductId(productId);
    }
}