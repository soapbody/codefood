package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	@Query("SELECT p FROM Pedido p " +
			"JOIN FETCH p.cliente u " +
			"JOIN FETCH p.restaurante r " +
			"WHERE p.codigo = :codigo")
	Optional<Pedido> findByCodigo (String codigo);
	@Query(value = "select date(p.data_criacao) as data_criacao, " +
			"count(p.id) as total_vendas, " +
			"sum(p.valor_total) as total_faturado " +
			"from pedido p " +
			"group by date(p.data_criacao)", nativeQuery = true)
	List<VendaDiaria> vendasDiarias();
}
