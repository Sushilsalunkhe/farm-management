package com.sbs.uibackend.repo;

import com.sbs.uibackend.entity.FarmEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmEmployeeRepository extends JpaRepository<FarmEmployee, Long> {
    List<FarmEmployee> findByFarmId(Long farmId);
}

