package com.enterprisevelez.expense.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprisevelez.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
	
}

