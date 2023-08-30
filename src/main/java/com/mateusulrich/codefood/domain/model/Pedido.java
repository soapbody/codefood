package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;
	private String codigo;
	@Column(nullable = false)
	private BigDecimal subtotal;
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp with time zone")
	private OffsetDateTime dataCriacao;
	@Column(columnDefinition = "timestamp with time zone")
	private OffsetDateTime dataConfirmacao;
	@Column(columnDefinition = "timestamp with time zone")
	private OffsetDateTime dataCancelamento;
	@Column(columnDefinition = "timestamp with time zone")
	private OffsetDateTime dataEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<> ();

	@ManyToOne
	@JoinColumn (name = "usuario_cliente_id")
	private Usuario cliente;

	@Embedded
	private Endereco enderecoEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "forma_pagamento_id")
	private FormaPagamento formaPagamento;

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);
		this.subtotal = getItens().stream()
				.map(item -> item.getPrecoTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}
	public void confirmar() {
		setStatus (StatusPedido.CONFIRMADO);
		setDataConfirmacao (OffsetDateTime.now ());
	}
	public void entregar() {
		setStatus (StatusPedido.ENTREGUE);
		setDataEntrega (OffsetDateTime.now ());
	}
	public void cancelar() {
		setStatus (StatusPedido.CANCELADO);
		setDataCancelamento (OffsetDateTime.now ());
	}
	private void setStatus(StatusPedido novoStatus) {
		if (getStatus ().naoPodeAlterarPara (novoStatus)) {
			throw new NegocioException (String.format ("Status do pedido %s não pode ser alterado de %s para %s",
					getCodigo (), getStatus ().getDescricao (), novoStatus.getDescricao ()));
		}
		this.status = novoStatus;
	}

	public void definirFrete() {
		setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidoAosItens() {
		getItens().forEach(item -> item.setPedido(this));
	}
	@PrePersist
	private void gerarCodigo() {
		setCodigo (UUID.randomUUID ().toString ());
	}

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
