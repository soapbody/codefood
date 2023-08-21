package com.mateusulrich.codefood.controller;

import com.mateusulrich.codefood.domain.model.Estado;
import com.mateusulrich.codefood.domain.repository.EstadoRepository;
import com.mateusulrich.codefood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/estados")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CadastroEstadoService cadastroEstado;
	@GetMapping
	public List<Estado> listar () {
		return estadoRepository.findAll ();
	}
	@GetMapping ("/{estadoId}")
	public Estado buscar (@PathVariable Long estadoId) {
		return cadastroEstado.buscarEstado (estadoId);

	}
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public Estado adicionar (@Valid @RequestBody Estado estado) {
		return cadastroEstado.salvar (estado);
	}

	@PutMapping ("/{estadoId}")
	public Estado atualizar (@Valid @PathVariable Long estadoId,
							 @RequestBody Estado estado) {
		Estado estadoAtual = cadastroEstado.buscarEstado (estadoId);
		BeanUtils.copyProperties (estado, estadoAtual, "id");
		return cadastroEstado.salvar (estadoAtual);

	}
	@DeleteMapping ("/{estadoId}")
	@ResponseStatus (value = HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long estadoId) {
		cadastroEstado.excluir (estadoId);
	}
}