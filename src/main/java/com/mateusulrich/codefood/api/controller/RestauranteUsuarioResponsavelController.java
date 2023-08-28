package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.UsuarioMapper;
import com.mateusulrich.codefood.api.model.UsuarioDTO;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	@Autowired
	private UsuarioMapper usuarioMapper;
	@GetMapping
	public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarRestaurante (restauranteId);
		return usuarioMapper.toCollectionDTO (restaurante.getResponsaveis ());
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
	}

	@PutMapping ("/{usuarioId}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
	}
}
