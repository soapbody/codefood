package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.domain.model.Grupo;
import com.mateusulrich.codefood.domain.model.Permissao;
import com.mateusulrich.codefood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	@GetMapping
	public List<Permissao> listar (@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarGrupo (grupoId);
		return grupo.getPermissoes ();
	}
	@DeleteMapping ("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociarPermissao (grupoId, permissaoId);
	}
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associarPermissao (grupoId, permissaoId);
	}
}
