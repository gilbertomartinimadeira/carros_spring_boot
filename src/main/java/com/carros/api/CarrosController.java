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
import com.carros.domain.dto.CarroDTO;
import com.carros.services.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public ResponseEntity<List<CarroDTO>> get(){
		
		
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getByid(@PathVariable Long id) {
			
		Optional<CarroDTO> optionalCarro = service.getCarroById(id);
		
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
	public ResponseEntity<List<CarroDTO>> getByTipo(@PathVariable("tipo") String tipo){
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
		
	}
	
	@PostMapping
	public String post(@RequestBody CarroDTO dto) {
		Carro carroGravado = service.save(dto);
		
		return "Carro salvo com sucesso: " + carroGravado.getId();
		
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody CarroDTO dto) {
		
		Carro carroPersistido = service.update(dto,id);
		
		return "Carro atualizado com sucesso: " + carroPersistido.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso: " + id;
		
	}

}
