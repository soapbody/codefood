package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.PermissaoNaoEncontradaException;
import com.mateusulrich.codefood.domain.model.Permissao;
import com.mateusulrich.codefood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao buscarPermissao(Long id) {
		return permissaoRepository.findById (id).orElseThrow (()-> new PermissaoNaoEncontradaException (id));
	}
}
