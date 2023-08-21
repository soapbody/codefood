package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findComFreteGratis(String nome);
}
