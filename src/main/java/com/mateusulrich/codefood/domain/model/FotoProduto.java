package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.api.model.input.FotoProdutoInput;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Entity
@Getter
@Setter
public class FotoProduto {
	@Id
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	private String nomeArquivo;
	private String contentType;
	private String descricao;
	private Long tamanho;

	public FotoProduto () {
	}

	public FotoProduto (Produto produto, FotoProdutoInput fotoProdutoInput, MultipartFile arquivo) {
		this.id = produto.getId ();
		this.setProduto (produto);
		this.descricao = fotoProdutoInput.getDescricao ();
		this.contentType = arquivo.getContentType ();
		this.tamanho = arquivo.getSize ();
		this.nomeArquivo = arquivo.getOriginalFilename ();
	}
	public Long getRestauranteId() {
		if (getProduto () != null) {
			return getProduto ().getRestaurante ().getId ();
		}
		return null;
	}

	@Override
	public boolean equals (Object o) {

		if (this == o) return true;
		if (o == null || getClass () != o.getClass ()) return false;
		FotoProduto that = (FotoProduto) o;
		return Objects.equals (id, that.id);
	}

	@Override
	public int hashCode () {
		return Objects.hash (id);
	}
}
