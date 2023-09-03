package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.model.projection.VendaDiaria;
import com.mateusulrich.codefood.domain.repository.PedidoRepository;
import com.mateusulrich.codefood.domain.service.VendaQueryService;
import com.mateusulrich.codefood.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

	@Autowired
	private VendaQueryService vendaQueryService;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private VendaReportService vendaReportService;
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
													@RequestParam(required = false,
													defaultValue = "-03:00")String timeOffset) {
		return vendaQueryService.consultarVendasDiarias (filter, timeOffset);
	}

	@GetMapping(path = "/vendas-diarias-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filter,
																 @RequestParam(required = false,
														defaultValue = "-03:00")String timeOffset) {

		byte[] bytesPdf = vendaReportService.emitirVendasDiarias (filter, timeOffset);

		var headers = new HttpHeaders ();
		headers.add (HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Vendas-diarias.pdf");

		return ResponseEntity.ok ()
				.contentType (MediaType.APPLICATION_PDF)
				.headers (headers)
				.body (bytesPdf);
	}

	@GetMapping("/vendas-diarias2")
	public List<VendaDiaria> consultarVendasDiarias2() {
		return pedidoRepository.vendasDiarias ();
	}
}
