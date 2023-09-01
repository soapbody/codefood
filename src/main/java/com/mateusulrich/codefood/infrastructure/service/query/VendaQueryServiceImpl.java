package com.mateusulrich.codefood.infrastructure.service.query;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.model.enums.StatusPedido;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;
import com.mateusulrich.codefood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<VendaDiaria> consultarVendasDiarias (VendaDiariaFilter filter, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery (VendaDiaria.class);
		var root = query.from (Pedido.class);
		var predicates = new ArrayList<Predicate> ();

		var functionAtTimeZone = builder.function(
				"my_at_time_zone", Date.class, root.get("dataCriacao"),builder.literal(timeOffset));
		/*
		var functionConvertTzDataCriacao = builder.function (
				"convert_tz", LocalDate.class, root.get ("dataCriacao"),
				builder.literal ("+00:00"), builder.literal (timeOffset));

		 */

		var functionDateDataCriacao = builder.function ("date", Date.class, functionAtTimeZone);

		var selection = builder.construct (VendaDiaria.class,
				functionDateDataCriacao,
				builder.count (root.get ("id")),
				builder.sum (root.get ("valorTotal")));

		if (filter.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
		}

		if (filter.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
					filter.getDataCriacaoInicio()));
		}

		if (filter.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
					filter.getDataCriacaoFim()));
		}

		predicates.add(root.get("status").in(
				StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		query.select (selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy (functionDateDataCriacao);

		List<VendaDiaria> vendasDiarias = manager.createQuery(query).getResultList();
		for (VendaDiaria vendaDiaria : vendasDiarias) {
			System.out.println("Data recuperada: " + vendaDiaria.getData());
		}

		return manager.createQuery (query).getResultList ();
	}
}
