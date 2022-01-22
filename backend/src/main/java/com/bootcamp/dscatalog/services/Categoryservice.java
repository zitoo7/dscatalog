package com.bootcamp.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.dscatalog.entities.Category;
import com.bootcamp.dscatalog.repositories.CategoryRepository;

@Service
public class Categoryservice {

	@Autowired	
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
}
