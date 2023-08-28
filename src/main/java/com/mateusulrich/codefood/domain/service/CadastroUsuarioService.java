package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.exception.UsuarioNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Grupo;
import com.mateusulrich.codefood.domain.model.Usuario;
import com.mateusulrich.codefood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CadastroGrupoService cadastroGrupoService;

	public static final String MSG_USUARIO_EM_USO = "O Usuario de código %d não pode ser removido, pois está em uso";

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll ();
	}
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(Long usuarioId) {
		return usuarioRepository.findById (usuarioId).orElseThrow (()-> new UsuarioNaoEncontradoException (usuarioId));
	}
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioGrupo(Long usuarioId) {
		return usuarioRepository.findByIdFetchGrupos(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach (usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail (usuario.getEmail ());

		if (usuarioExistente.isPresent () && !usuarioExistente.get ().equals (usuario)) {
			throw new NegocioException (
					String.format ("Já existe um usuario cadastrado com o email %s", usuario.getEmail ()));
		}
		return usuarioRepository.save (usuario);
	}
	@Transactional
	public void excluir(Long id) {
		try {
			usuarioRepository.deleteById (id);
			usuarioRepository.flush ();

		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException (id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format (MSG_USUARIO_EM_USO, id));
		}
	}
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarUsuario (usuarioId);
		if (usuario.senhaNaoCoincideCom (senhaAtual)) {
			throw new NegocioException ("Senha atual informada não coincide com a senha do usuário.");
		}
		usuario.setSenha (novaSenha);
	}
	@Transactional
	public void associarGrupo (Long usuarioId, Long grupoId) {
		Usuario usuario = buscarUsuario (usuarioId);
		Grupo grupo = cadastroGrupoService.buscarGrupo (grupoId);
		usuario.inserirGrupo (grupo);
	}
	@Transactional
	public void desassociarGrupo (Long usuarioId, Long grupoId) {
		Usuario usuario = buscarUsuario (usuarioId);
		Grupo grupo = cadastroGrupoService.buscarGrupo (grupoId);
		usuario.removerGrupo (grupo);
	}
}
