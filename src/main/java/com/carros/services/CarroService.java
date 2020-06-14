package com.carros.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carros.domain.Carro;
import com.carros.repository.CarroRepository;


@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;
	
	public Iterable<Carro> getCarros(){
		
		return repository.findAll();
	
	}

	public Optional<Carro> getCarroById(Long id) { 
		return repository.findById(id);
	}

	public Iterable<Carro> getCarrosByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public Carro save(Carro carro) {

		return repository.save(carro);
	}
	
}
