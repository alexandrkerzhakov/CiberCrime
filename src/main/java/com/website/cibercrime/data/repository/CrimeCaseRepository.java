package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.CrimeCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrimeCaseRepository extends JpaRepository<CrimeCase, Long> {
}
