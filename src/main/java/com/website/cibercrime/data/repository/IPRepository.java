package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.IP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPRepository extends JpaRepository<IP, Long> {
}
