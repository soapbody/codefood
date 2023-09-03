package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.FotoProduto;
import com.mateusulrich.codefood.domain.model.Produto;
import com.mateusulrich.codefood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	@Query ("from Produto where restaurante.id = :restaurante and id = :produto")
	Optional<Produto> findById(@Param ("restaurante") Long restauranteId,
							   @Param("produto") Long produtoId);

	List<Produto> findByRestaurante(Restaurante restaurante);
	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	@Query("select f from FotoProduto f " +
			"join f.produto p " +
			"where p.restaurante.id = :restauranteId " +
			"and f.produto.id = :produtoId")
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
	@Query("select f.nomeArquivo from FotoProduto f where f.produto.restaurante.id = :restauranteId and f.produto.id = :produtoId")
	String getNomeArquivoByProdutoRestauranteID (Long restauranteId, Long produtoId);


}
