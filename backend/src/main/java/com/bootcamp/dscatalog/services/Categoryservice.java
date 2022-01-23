package com.bootcamp.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.dscatalog.dto.CategoryDTO;
import com.bootcamp.dscatalog.entities.Category;
import com.bootcamp.dscatalog.repositories.CategoryRepository;
import com.bootcamp.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired	
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		List<CategoryDTO> listDTO = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity does not exist!"));
		return new CategoryDTO(entity);
	}
	
	public Category insert(Category obj) {
		return repository.saveAndFlush(obj);
	}
	
	public Category update(Long id, Category obj) {
		Category entity = repository.findById(id).get();
		updateData(entity, obj);
		return repository.saveAndFlush(entity);
		
	}
	
	public void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
 