package com.carros.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	public Carro update(Carro carro, Long id) {
		// TODO Auto-generated method stub
		Assert.notNull(id, "Não foi possível atualizar o registro");
		
		Optional<Carro> optionalCarro = getCarroById(id);
		
		if(!optionalCarro.isPresent()) {
			throw new RuntimeException("Não foi possível atualizar o registro");
		} else {
			
			Carro carroPersistido = optionalCarro.get();
			
			carroPersistido.setNome(carro.getNome());
			carroPersistido.setTipo(carro.getTipo());
			
			System.out.println("Carro id: " + carroPersistido.getId());
			
			repository.save(carroPersistido);
			
			return carroPersistido;
		}
	}

	public void delete(Long id) {
		
		Optional<Carro> optionalCarro = getCarroById(id);
		
		if(!optionalCarro.isPresent()) {
			throw new RuntimeException("registro não encontrado");
		}else {		
			repository.delete(optionalCarro.get());
		}
	
	}
	
}
