package com.sbs.uibackend.repo;

import com.sbs.uibackend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}

