package com.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Iterable<Carro>> get(){
		
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> getByid(@PathVariable Long id) {
		Optional<Carro> optionalCarro = service.getCarroById(id);
		
		return optionalCarro
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
		
		/*if(!optionalCarro.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(optionalCarro.get());
		*/
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Carro>> getByTipo(@PathVariable("tipo") String tipo){
		List<Carro> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
		
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro carroGravado = service.save(carro);
		
		return "Carro salvo com sucesso: " + carroGravado.getId();
		
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro carroPersistido = service.update(carro,id);
		
		return "Carro atualizado com sucesso: " + carroPersistido.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso: " + id;
		
	}

}
