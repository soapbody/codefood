package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
//@JsonFilter ("pedidoFilter")
@Getter
@Setter
public class PedidoMinDTO {
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private RestauranteMinDTO restaurante;
	private UsuarioDTO cliente;

}
