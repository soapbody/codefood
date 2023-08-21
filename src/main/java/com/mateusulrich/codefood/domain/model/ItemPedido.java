package com.mateusulrich.codefood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ItemPedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn (name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JoinColumn (name = "pedido_id")
	private Pedido pedido;

	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;

	private String observacao;

}
