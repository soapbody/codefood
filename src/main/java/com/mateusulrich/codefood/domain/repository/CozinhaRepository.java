package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	List<Cozinha> findAllByNomeContaining(String nome);
	Optional<Cozinha> findByNome(String nome);
	boolean existsByNome(String nome);

}
