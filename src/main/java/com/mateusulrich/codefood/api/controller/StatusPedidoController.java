package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.domain.service.StatusPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos/{codigo}")
public class StatusPedidoController {
	@Autowired
	private StatusPedidoService statusPedidoService;
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String codigo) {
		statusPedidoService.confirmar (codigo);

	}
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String codigo) {
		statusPedidoService.cancelamento (codigo);

	}
	@PutMapping("/entregado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable String codigo) {
		statusPedidoService.pedidoEntregado (codigo);

	}
}
