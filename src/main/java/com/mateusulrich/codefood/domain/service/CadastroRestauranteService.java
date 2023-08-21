package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.RestauranteNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadastroCozinhaService cozinhaService;

	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cozinhaService.buscarCozinha (restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save (restaurante);
	}
	public Restaurante buscarRestaurante (Long restauranteId) {
		return restauranteRepository.findById (restauranteId).orElseThrow (() -> new RestauranteNaoEncontradoException (restauranteId));
	}
}
