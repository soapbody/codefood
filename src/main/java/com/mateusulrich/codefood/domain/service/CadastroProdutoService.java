package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.ProdutoNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Produto;
import com.mateusulrich.codefood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Transactional(readOnly = true)
	public Produto buscarProdutoPorRestaurante(Long produtoId, Long restauranteId) {
		return produtoRepository.findById(produtoId, restauranteId).orElseThrow(() -> new ProdutoNaoEncontradoException (produtoId));
	}
	@Transactional
	public Produto salvar (Produto produto) {
		return produtoRepository.save (produto);

	}
}
