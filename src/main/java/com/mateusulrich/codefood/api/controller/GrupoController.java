package com.mateusulrich.codefood.api.controller;


import com.mateusulrich.codefood.api.assembler.GrupoMapper;
import com.mateusulrich.codefood.api.model.GrupoDTO;
import com.mateusulrich.codefood.domain.exception.GrupoNaoEncontradoException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.Grupo;
import com.mateusulrich.codefood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GrupoController {
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	@Autowired
	private GrupoMapper grupoMapper;
	@GetMapping ("/grupos")
	public List<GrupoDTO> buscarTodosGrupos() {
		return grupoMapper.toCollectionDTO (cadastroGrupoService.buscarTodos ());
	}
	@GetMapping ("/grupos/{grupoId}")
	public GrupoDTO buscarGrupoPorId(Long grupoId) {
		return grupoMapper.toDTO (cadastroGrupoService.buscarGrupo (grupoId));
	}
	@PostMapping ("/grupos")
	@ResponseStatus (HttpStatus.CREATED)
	public GrupoDTO adicionarGrupo(GrupoDTO grupoDTO) {
		//return grupoMapper.toDTO (cadastroGrupoService.salvar (grupoMapper.toDomainObject (grupoDTO)));
		try {
			Grupo grupo = grupoMapper.toDomainObject (grupoDTO);
			return grupoMapper.toDTO (cadastroGrupoService.salvar (grupo));
		}catch (GrupoNaoEncontradoException e) {
			throw new NegocioException (e.getMessage ());
		}

	}

	@PutMapping ("/{cozinhaId}")
	public GrupoDTO atualizar(@Valid @PathVariable Long grupoId,
								@RequestBody GrupoDTO grupoDTO) {

		Grupo grupoAtual = cadastroGrupoService.buscarGrupo (grupoId);
		grupoMapper.copyToDomainObject (grupoDTO, grupoAtual);
		return grupoMapper.toDTO (cadastroGrupoService.salvar(grupoAtual));
	}

	@DeleteMapping ("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId ){
		cadastroGrupoService.excluir (grupoId);
	}
}
