package com.mateusulrich.codefood.infrastructure.repository;

import com.mateusulrich.codefood.domain.model.FotoProduto;
import com.mateusulrich.codefood.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;
	@Override
	public FotoProduto save (FotoProduto fotoProduto) {
		return manager.merge (fotoProduto);
	}
	@Transactional
	@Override
	public void delete (FotoProduto fotoProduto) {
		manager.remove (fotoProduto);
	}

}

