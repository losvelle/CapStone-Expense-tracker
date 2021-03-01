package com.enterprisevelez.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisevelez.expense.model.Category;
import com.enterprisevelez.expense.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {

	private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping("/categories")
	Collection<Category> categories(){
		return categoryRepository.findAll();
//		selects * from category table
	}
	
//	select a specific category, pass a Long type Id using a pathvariable, 
//	In case no ID is returned we use OPTIONAL to create a response not found 
//	In the case there is a return the response will be put in the body and sent back to the browser
	
	@GetMapping("/categories/{id}")
	ResponseEntity<?> getCategory(@PathVariable Long Id){
		Optional<Category> category = categoryRepository.findById(Id);
		return category.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/category")
	ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException{
		Category result = categoryRepository.save(category);
		return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
//		this is similar to insert into table.. but handled by JPA not the DB		
	}
	
	@PutMapping("/category/{id}")
	ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category){
		Category result = categoryRepository.save(category);
		return ResponseEntity.ok().body(result);
// this lets you update any category. 	
	}
	
	@DeleteMapping("/category/{id}")
	ResponseEntity<?> deleteCategory(@PathVariable Long id){
		categoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
