package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.EstadoMapper;
import com.mateusulrich.codefood.api.model.EstadoDTO;
import com.mateusulrich.codefood.domain.model.Estado;
import com.mateusulrich.codefood.domain.repository.EstadoRepository;
import com.mateusulrich.codefood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/estados")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CadastroEstadoService cadastroEstado;
	@Autowired
	private EstadoMapper estadoMapper;
	@GetMapping
	public List<EstadoDTO> listar () {
		return estadoMapper.toCollectionDTO (estadoRepository.findAll ());
	}
	@GetMapping ("/{estadoId}")
	public EstadoDTO buscar (@PathVariable Long estadoId) {
		return estadoMapper.toDTO (cadastroEstado.buscarEstado (estadoId));

	}
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public EstadoDTO adicionar (@Valid @RequestBody EstadoDTO estadoDTO) {
		Estado estado = estadoMapper.toDomainObject (estadoDTO);
		return estadoMapper.toDTO (cadastroEstado.salvar (estado));
	}

	@PutMapping ("/{estadoId}")
	public EstadoDTO atualizar (@Valid @PathVariable Long estadoId,
							 @RequestBody EstadoDTO estadoDTO) {
		Estado estadoAtual = cadastroEstado.buscarEstado (estadoId);
		//BeanUtils.copyProperties (estado, estadoAtual, "id");
		estadoMapper.copyToDomainObject (estadoDTO, estadoAtual);
		return estadoMapper.toDTO (cadastroEstado.salvar (estadoAtual));

	}
	@DeleteMapping ("/{estadoId}")
	@ResponseStatus (value = HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long estadoId) {
		cadastroEstado.excluir (estadoId);
	}
}