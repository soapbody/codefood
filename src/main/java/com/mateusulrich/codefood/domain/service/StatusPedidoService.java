package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusPedidoService {
	@Autowired
	private CadastroPedidoService pedidoService;
	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = pedidoService.buscarPedido(codigo);
		pedido.confirmar ();
	}
	@Transactional
	public void cancelamento (String codigo) {
		Pedido pedido = pedidoService.buscarPedido(codigo);
		pedido.cancelar();
	}
	@Transactional
	public void pedidoEntregado (String codigo) {
		Pedido pedido = pedidoService.buscarPedido(codigo);
		pedido.entregar ();

	}
}
