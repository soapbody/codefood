package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.FormaPagamentoMapper;
import com.mateusulrich.codefood.api.model.FormaPagamentoDTO;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	@Autowired
	private FormaPagamentoMapper formaPagamentoMapper;
	@GetMapping
	public List<FormaPagamentoDTO> listar (@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarRestaurante (restauranteId);
		return formaPagamentoMapper.toCollectionDTO (restaurante.getFormasPagamento ());
	}
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento (restauranteId, formaPagamentoId);
	}

	@PutMapping("/{formaPagamentoId}")
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento (restauranteId, formaPagamentoId);
	}
}