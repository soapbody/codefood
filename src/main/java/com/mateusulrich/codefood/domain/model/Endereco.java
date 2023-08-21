package com.mateusulrich.codefood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Embeddable
public class Endereco {
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	@ManyToOne
	@JoinColumn (name = "cidade_id")
	private Cidade cidade;
}
