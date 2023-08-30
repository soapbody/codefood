package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;

import java.util.List;

public interface VendaQueryService {
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
