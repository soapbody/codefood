package com.mateusulrich.codefood.api.model;

import com.mateusulrich.codefood.api.model.input.CozinhaIdInput;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaDTO {
	private CozinhaIdInput id;
	private String nome;
	private String descricao;

}
