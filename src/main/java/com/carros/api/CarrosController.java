package com.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.domain.Carro;
import com.carros.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public Iterable<Carro> get(){
		return service.getCarros();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Carro>> getByid(@PathVariable Long id) {
		Optional<Carro> carroPersistido = service.getCarroById(id);
		
		return ResponseEntity.ok(carroPersistido);
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<Iterable<Carro>> getByTipo(@PathVariable("tipo") String tipo){
		return ResponseEntity.ok(service.getCarrosByTipo(tipo));
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro carroGravado = service.save(carro);
		
		return "Carro salvo com sucesso: " + carroGravado.getId();
		
	}

}
