package com.sbs.uibackend.repo;

import com.sbs.uibackend.entity.FarmProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmProductRepository extends JpaRepository<FarmProduct, Long> {
    Page<FarmProduct> findByFarmId(Long farmId, Pageable pageable);

    Page<FarmProduct> findByFarmIdAndProductNameContainingIgnoreCase(
            Long farmId, String productName, Pageable pageable);
}
