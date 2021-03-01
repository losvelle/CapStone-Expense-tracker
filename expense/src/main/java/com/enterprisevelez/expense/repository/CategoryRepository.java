package com.enterprisevelez.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprisevelez.expense.model.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {
	
	Category findByName(String name);
		

}
