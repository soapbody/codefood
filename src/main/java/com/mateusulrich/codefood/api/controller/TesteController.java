package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.repository.CozinhaRepository;
import com.mateusulrich.codefood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> findAllByNomeContaining(String nome) {
		return cozinhaRepository.findAllByNomeContaining (nome);
	}

	@GetMapping ("/cozinhas/unica-por-nome")
	public Optional<Cozinha> findByNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	@GetMapping ("/restaurantes/por-taxa-frete")
	public List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween (taxaInicial, taxaFinal);
	}
	@GetMapping ("/restaurantes/por-nome-e-taxa-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find (nome, taxaInicial, taxaFinal);
	}
	@GetMapping ("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {

		return restauranteRepository.findComFreteGratis (nome);
	}
	@GetMapping ("/restaurantes/por-nome-e-cozinha-id")
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome (nome, cozinhaId);
	}
	@GetMapping ("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining (nome);
	}

	@GetMapping ("/restaurantes/top-2-por-nome")
	public List<Restaurante> findTop2ByNomeContaining(String nome) {
		return restauranteRepository.findTop2ByNomeContaining (nome);
	}
	@GetMapping ("/cozinhas/exists")
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome (nome);
	}
	@GetMapping ("/restaurantes/countByCozinhaId")
	public int countByCozinhaId(Long id) {
		return restauranteRepository.countByCozinhaId (id);
	}
	@GetMapping ("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro ();
	}
	@GetMapping ("/cozinhas/primeira")
	public Optional<Cozinha> cozinhaPrimeira() {
		return cozinhaRepository.buscarPrimeiro ();
	}




}
