package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.exception.GrupoNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Grupo;
import com.mateusulrich.codefood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService {
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;

	public static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

	public List<Grupo> buscarTodos() {
		return grupoRepository.findAll ();
	}
	public Grupo buscarGrupo(Long id) {
		return grupoRepository.findById (id).orElseThrow (()-> new GrupoNaoEncontradoException (id));
	}
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save (grupo);
	}
	@Transactional
	public void excluir(Long id) {
		try {
			grupoRepository.deleteById (id);
			grupoRepository.flush ();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException (id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format (MSG_GRUPO_EM_USO, id));
		}
	}
	@Transactional
	public void desassociarPermissao (Long grupoId, Long permissaoId) {
		Grupo grupo = buscarGrupo (grupoId);
		grupo.removerPermissao (
				cadastroPermissaoService.buscarPermissao (permissaoId));
	}

	@Transactional
	public void associarPermissao (Long grupoId, Long permissaoId) {
		Grupo grupo = buscarGrupo (grupoId);

		grupo.inserirPermissao (
				cadastroPermissaoService.buscarPermissao (permissaoId));
	}

}
