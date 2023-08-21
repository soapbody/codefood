package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.LocalDateTimeConverter;
import com.mateusulrich.codefood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;
	@Column(nullable = false)
	private BigDecimal subTotal;
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataCriacao;

	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedido;

	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn (name = "cliente_id")
	private Usuario cliente;

	@Embedded
	private Endereco enderecoEntrega;

	@ManyToOne
	@JoinColumn (name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass () != o.getClass ()) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals (id, pedido.id);
	}

	@Override
	public int hashCode () {
		return Objects.hash (id);
	}
}
