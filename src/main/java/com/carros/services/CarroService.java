package com.carros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.Carro;
import com.carros.domain.dto.CarroDTO;
import com.carros.repository.CarroRepository;


@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;
	
	public List<CarroDTO> getCarros(){
		
		return repository.findAll().stream()
				.map(CarroDTO::new).collect(Collectors.toList()); 
			
	}

	public Optional<CarroDTO> getCarroById(Long id) { 
		return repository.findById(id).map(CarroDTO::new);
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		
		return repository.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
	}

	public Carro save(CarroDTO dto) {

		Carro c = new Carro(dto);
		
		return repository.save(c);
	}

	public Carro update(CarroDTO dto, Long id) {
		// TODO Auto-generated method stub
		Assert.notNull(id, "Não foi possível atualizar o registro");
		
		Optional<Carro> optionalCarro = repository.findById(id);
		
		if(!optionalCarro.isPresent()) {
			throw new RuntimeException("Não foi possível atualizar o registro");
		} else {
			
			Carro carroPersistido = optionalCarro.get();
			
			carroPersistido.setNome(dto.getNome());
			carroPersistido.setTipo(dto.getTipo());
			
			System.out.println("Carro id: " + carroPersistido.getId());
			
			repository.save(carroPersistido);
			
			return carroPersistido;
		}
	}

	public void delete(Long id) {
		
		Optional<CarroDTO> optionalCarro = getCarroById(id);
		
		if(!optionalCarro.isPresent()) {
			throw new RuntimeException("registro não encontrado");
		}else {		
			//repository.delete(optionalCarro.get());
			repository.deleteById(id);
		}
	
	}
	
}
