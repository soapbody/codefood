package com.mateusulrich.codefood.infrastructure.repository.spec;

import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;


public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
		return (root, query, criteriaBuilder) -> {
			if (Pedido.class.equals (query.getResultType ())) {
				root.fetch ("cliente");
				root.fetch ("restaurante").fetch ("cozinha");
			}
			var predicates = new ArrayList<Predicate> ();
			if (filter.getClienteId () != null) {
				predicates.add (criteriaBuilder.equal (root.get ("cliente"), filter.getClienteId ()));
			}
			if (filter.getRestauranteId () != null) {
				predicates.add (criteriaBuilder.equal (root.get ("restaurante"), filter.getRestauranteId ()));
			}
			if (filter.getDataCriacaoInicio () != null) {
				predicates.add (criteriaBuilder.greaterThanOrEqualTo (root.get ("dataCriacao"), filter.getDataCriacaoInicio ()));
			}
			if (filter.getDataCriacaoFim () != null) {
				predicates.add (criteriaBuilder.lessThanOrEqualTo (root.get ("dataCriacao"), filter.getDataCriacaoFim ()));
			}
			return criteriaBuilder.and(predicates.toArray (new Predicate[0]));
		};

	}
}
