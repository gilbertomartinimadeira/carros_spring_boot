package com.carros.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.carros.domain.dto.CarroDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro {
	
	public Carro(CarroDTO dto) {
		// TODO Auto-generated constructor stub
		this.nome = dto.getNome();
		this.tipo = dto.getTipo();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nome;
	private String tipo;
	
}
