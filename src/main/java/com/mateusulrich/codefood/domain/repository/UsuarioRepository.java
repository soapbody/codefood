package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail (String email);

	@Query ("SELECT u FROM Usuario u JOIN FETCH u.grupos WHERE u.id = :id")
	Optional<Usuario> findByIdFetchGrupos(@Param ("id") Long id);
}
