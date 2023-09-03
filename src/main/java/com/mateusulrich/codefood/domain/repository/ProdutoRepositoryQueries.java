package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	FotoProduto save(FotoProduto fotoProduto);
	void delete(FotoProduto fotoProduto);
}
