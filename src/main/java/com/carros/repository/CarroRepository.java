package com.carros.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carros.domain.Carro;

public interface CarroRepository extends CrudRepository<Carro,Long> {

	List<Carro> findByTipo(String tipo);	
}
