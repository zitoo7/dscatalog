package com.bootcamp.CRUDTask.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.CRUDTask.DTO.ClientDTO;
import com.bootcamp.CRUDTask.entities.Client;
import com.bootcamp.CRUDTask.repositories.ClientRepository;
import com.bootcamp.CRUDTask.services.exceptions.DatabaseException;
import com.bootcamp.CRUDTask.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest request) {
		Page<Client> list = repository.findAll(request);
		Page<ClientDTO> listDto = list.map(x -> new ClientDTO(x));
		return listDto;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity does not exist!"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		fromDtoToEntity(entity, dto);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	public void fromDtoToEntity(Client entity, ClientDTO dto) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			fromDtoToEntity(entity, dto);
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found!");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation!");
		}
	}

}
