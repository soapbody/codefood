package com.mateusulrich.codefood.infrastructure.service.report;

import com.mateusulrich.codefood.domain.filter.VendaDiariaFilter;
import com.mateusulrich.codefood.domain.service.VendaQueryService;
import com.mateusulrich.codefood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class JasperVendaReportService implements VendaReportService {

	@Autowired
	private VendaQueryService vendaQueryService;
	@Override
	public byte[] emitirVendasDiarias (VendaDiariaFilter filtro, String timeOffset) {
		try {
			var inputStream = this.getClass ().getResourceAsStream ("/relatorios/Vendas-diarias.jasper");
			var parametros = new HashMap<String, Object> ();
			parametros.put("REPORT_LOCALE", new Locale ("pt", "BR"));
			var vendasDiarias = vendaQueryService.consultarVendasDiarias (filtro, timeOffset);
			var dataSource = new JRBeanCollectionDataSource (vendasDiarias);
			var jasperPrint = JasperFillManager.fillReport (inputStream, parametros, dataSource);
			return JasperExportManager.exportReportToPdf (jasperPrint);
		} catch (JRException e) {
			throw new ReportException ("Não foi possível emitir o relatório de vendas diárias, " + e.getMessage ());
		}
	}
}
