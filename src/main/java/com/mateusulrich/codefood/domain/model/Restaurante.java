package com.mateusulrich.codefood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateusulrich.codefood.LocalDateTimeConverter;
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
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	//@PositiveOrZero
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

	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<> ();

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime creationDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime lastUpdate;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produto;

}
