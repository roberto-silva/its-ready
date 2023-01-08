package com.roberto.taPronto.repository;

import com.roberto.taPronto.domain.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Page<Budget> findAllBudgetByNameContainsIgnoreCase(Pageable pageable, String search);
}
