package com.sbs.uibackend.service;

import com.sbs.uibackend.entity.Farm;
import com.sbs.uibackend.entity.FarmProduct;
import com.sbs.uibackend.repo.FarmProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmProductServic {

    @Autowired
    private FarmProductRepository productRepository;

    /* ================= SAVE ================= */

    public FarmProduct saveProduct(FarmProduct product) {
        return productRepository.save(product);
    }

    /* ================= GET BY FARM (PAGINATION + SEARCH) ================= */

    public Page<FarmProduct> getProducts(
            Long farmId, int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size);

        if (search == null || search.isEmpty()) {
            return productRepository.findByFarmId(farmId, pageable);
        }

        return productRepository
                .findByFarmIdAndProductNameContainingIgnoreCase(
                        farmId, search, pageable);
    }

    /* ================= GET ALL PRODUCTS ================= */

    public List<FarmProduct> findAll() {
        return productRepository.findAll();
    }

    /* ================= DELETE ================= */

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}