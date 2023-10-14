package com.website.cibercrime.data.repository;

import com.website.cibercrime.data.entity.AccountVK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountVKRepository extends JpaRepository<AccountVK, Long> {
    @Query("select c.accountVKList from claimant c where c.id = :claimantId")
    List<AccountVK> findByClaimantId(Long claimantId);
}
