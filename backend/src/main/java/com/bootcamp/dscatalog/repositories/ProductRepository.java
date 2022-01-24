package com.bootcamp.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
