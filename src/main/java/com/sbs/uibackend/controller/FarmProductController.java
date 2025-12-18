package com.sbs.uibackend.controller;

import com.sbs.uibackend.entity.FarmProduct;
import com.sbs.uibackend.service.FarmProductServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmProductController {

    @Autowired
    private FarmProductServic productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FarmProduct addProduct(@RequestBody FarmProduct product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/farm/{farmId}")
    public Page<FarmProduct> getProducts(
            @PathVariable Long farmId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {
        return productService.getProducts(farmId, page, size, search);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}

