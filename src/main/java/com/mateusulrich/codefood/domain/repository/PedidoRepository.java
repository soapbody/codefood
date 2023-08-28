package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante")
	List<Pedido> findAll();
	@Query("SELECT p FROM Pedido p " +
			"JOIN FETCH p.cliente u " +
			"JOIN FETCH p.restaurante r " +
			"WHERE p.codigo = :codigo")
	Optional<Pedido> findByCodigo (String codigo);
}
