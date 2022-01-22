package com.bootcamp.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.dscatalog.entities.Category;
import com.bootcamp.dscatalog.repositories.CategoryRepository;

@RestController
@RequestMapping(value = "/categories")
public class categoryResource {
	
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		
		List<Category> list = categoryRepository.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	
	
}
