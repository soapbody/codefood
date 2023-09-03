package com.mateusulrich.codefood.api.model;

import com.mateusulrich.codefood.domain.model.FotoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDTO {
	private Long id;
	private String nomeArquivo;
	private String descricao;;
	private String contentType;
	private Long tamanho;
	public FotoProdutoDTO () {
	}

	public FotoProdutoDTO (FotoProduto fotoProduto) {
		this.id = fotoProduto.getId ();
		this.descricao = fotoProduto.getDescricao ();
		this.contentType = fotoProduto.getContentType ();
		this.tamanho = fotoProduto.getTamanho ();
		this.nomeArquivo = fotoProduto.getNomeArquivo ();
	}
}
