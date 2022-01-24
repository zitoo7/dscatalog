package com.bootcamp.CRUDTask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.CRUDTask.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
