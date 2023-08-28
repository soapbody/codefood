package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.core.validation.Groups;
import com.mateusulrich.codefood.core.validation.ValorZeroIncluiDescricao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ValorZeroIncluiDescricao (
		valorField = "taxaFrete",
		descricaoField = "nome",
		descricaoObrigatoria = "Frete Grátis"
)
@Data
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (nullable = false)
	@NotBlank (message = "Nome é obrigatório.")
	private String nome;

	@Column(name = "ativo")
	private Boolean ativo = Boolean.TRUE;

	private Boolean aberto;

	@PositiveOrZero
	//@TaxaFrete
	//@Multiplo (numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@ConvertGroup (to = Groups.CozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<> ();

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp with time zone")
	private OffsetDateTime creationDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "timestamp with time zone")
	private OffsetDateTime lastUpdate;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<> ();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<> ();
	public void ativar() {
		setAtivo (true);
	}
	public void inativar() {
		setAtivo (false);
	}
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento ().remove (formaPagamento);
	}
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento ().add (formaPagamento);
	}
	public void abrirRestaurante() {
		setAberto (true);
	}
	public void fecharRestaurante() {
		setAberto (false);
	}
	public boolean removerResponsavel(Usuario usuario) {
		return getResponsaveis().remove(usuario);
	}
	public boolean adicionarResponsavel(Usuario usuario) {
		return getResponsaveis().add(usuario);
	}
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}
}
