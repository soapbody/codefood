package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends
		CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {
	@Transactional
	@Modifying
	void deleteById (Long id);
	@Query(value = "from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento ")
	List<Restaurante> findAll();
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, Long id);
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	List<Restaurante> findTop2ByNomeContaining(String nome);
	int countByCozinhaId(Long id);

}
