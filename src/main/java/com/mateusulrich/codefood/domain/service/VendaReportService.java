package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
