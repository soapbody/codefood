package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;
import com.mateusulrich.codefood.domain.repository.PedidoRepository;
import com.mateusulrich.codefood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

	@Autowired
	private VendaQueryService vendaQueryService;

	@Autowired
	private PedidoRepository pedidoRepository;
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
		return vendaQueryService.consultarVendasDiarias (filter);
	}

	@GetMapping("/vendas-diarias2")
	public List<VendaDiaria> consultarVendasDiarias2() {
		return pedidoRepository.vendasDiarias ();
	}
}
