package com.sbs.uibackend.service;

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

    public FarmProduct saveProduct(FarmProduct product) {
        return productRepository.save(product);
    }

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

}
