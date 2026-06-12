package com.anurag.variant_graph.controller;

import com.anurag.variant_graph.entity.ProductVariant;
import com.anurag.variant_graph.repository.ProductVariantRepository;
import com.anurag.variant_graph.service.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
@RequiredArgsConstructor
public class VariantController {

    private final VariantService variantService;

    @GetMapping("/{productId}")
    public List<ProductVariant> getVariants(
            @PathVariable Long productId) {
        return variantService.getVariantsByProductId(productId);
    }
    @PostMapping()
    public ResponseEntity<ProductVariant> addVariants(@RequestBody ProductVariant variant){
        return ResponseEntity.ok(variantService.createVariant(variant));
    }
}
