package com.mateusulrich.codefood.infrastructure.service;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.model.enums.StatusPedido;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;
import com.mateusulrich.codefood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<VendaDiaria> consultarVendasDiarias (VendaDiariaFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery (VendaDiaria.class);
		var root = query.from (Pedido.class);
		var predicates = new ArrayList<Predicate> ();

		var functionDateDataCriacao = builder.function ("date", LocalDate.class, root.get("dataCriacao"));

		var selection = builder.construct (VendaDiaria.class,
				functionDateDataCriacao,
				builder.count (root.get ("id")),
				builder.sum (root.get ("valorTotal")));

		if (filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}

		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
					filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
					filtro.getDataCriacaoFim()));
		}

		predicates.add(root.get("status").in(
				StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		query.select (selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy (functionDateDataCriacao);

		return manager.createQuery (query).getResultList ();
	}
}
