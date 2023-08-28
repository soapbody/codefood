package com.mateusulrich.codefood.api.controller;


import com.mateusulrich.codefood.api.assembler.UsuarioMapper;
import com.mateusulrich.codefood.api.model.UsuarioDTO;
import com.mateusulrich.codefood.api.model.input.UsuarioAttInput;
import com.mateusulrich.codefood.api.model.input.UsuarioInput;
import com.mateusulrich.codefood.api.model.input.UsuarioSenhaAttInput;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.exception.UsuarioNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Usuario;
import com.mateusulrich.codefood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsuarioController {
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private UsuarioMapper usuarioMapper;
	@GetMapping ("/usuarios")
	public List<UsuarioDTO> buscarTodosUsuarios() {
		return usuarioMapper.toCollectionDTO (cadastroUsuarioService.buscarTodos ());
	}
	@GetMapping ("/usuarios/{usuarioId}")
	public UsuarioDTO buscarGrupoPorId(Long usuarioId) {
		return usuarioMapper.toDTO (cadastroUsuarioService.buscarUsuario (usuarioId));
	}
	@PostMapping ("/usuarios")
	@ResponseStatus (HttpStatus.CREATED)
	public UsuarioDTO adicionarUsuario(UsuarioInput usuarioInput) {
		//return grupoMapper.toDTO (cadastroGrupoService.salvar (grupoMapper.toDomainObject (grupoDTO)));
		try {
			Usuario usuario = usuarioMapper.toDomainObject (usuarioInput);
			return usuarioMapper.toDTO (cadastroUsuarioService.salvar (usuario));
		}catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException (e.getMessage ());
		}

	}

	@PutMapping ("/{usuarioId}")
	public UsuarioDTO atualizar(@Valid @PathVariable Long usuarioId,
								@RequestBody UsuarioAttInput usuarioAttInput) {

		Usuario usuarioAtual = cadastroUsuarioService.buscarUsuario (usuarioId);
		usuarioMapper.copyAttToDomainObject (usuarioAttInput, usuarioAtual);
		return usuarioMapper.toDTO (cadastroUsuarioService.salvar(usuarioAtual));
	}
	@PutMapping ("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioSenhaAttInput usuarioSenhaAttInput)
	{
		cadastroUsuarioService.alterarSenha (usuarioId, usuarioSenhaAttInput.getSenhaAtual (), usuarioSenhaAttInput.getSenhaNova ());
	}

	@DeleteMapping ("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId ){
		cadastroUsuarioService.excluir (usuarioId);
	}
}
