package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusPedidoService {
	@Autowired
	private CadastroPedidoService pedidoService;
	@Autowired
	private EnvioEmailService emailService;
	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = pedidoService.buscarPedido(codigo);
		pedido.confirmar ();
		var mensagem = Mensagem.builder ()
				.assunto (pedido.getRestaurante ().getNome () + " - Pedido Confirmado")
				.corpo ("O pedido de código <strong>"
				+ pedido.getCodigo () + "</strong> foi confirmado!")
				.destinatario (pedido.getCliente ().getEmail ())
				.build ();
		emailService.enviar (mensagem);
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
