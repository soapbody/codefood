package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.GrupoMapper;
import com.mateusulrich.codefood.api.model.GrupoDTO;
import com.mateusulrich.codefood.domain.model.Usuario;
import com.mateusulrich.codefood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private GrupoMapper grupoMapper;
	@GetMapping
	public List<GrupoDTO> buscarGrupos(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarUsuario (usuarioId);
		return grupoMapper.toCollectionDTO (usuario.getGrupos ());
	}
	@DeleteMapping ("/{grupoId}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.desassociarGrupo (usuarioId, grupoId);
	}
	@PutMapping ("/{grupoId}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.associarGrupo (usuarioId, grupoId);
	}
}
