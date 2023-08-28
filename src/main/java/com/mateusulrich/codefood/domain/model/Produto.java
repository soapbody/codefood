package com.mateusulrich.codefood.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Produto {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurante_id", nullable = false, foreignKey = @ForeignKey(name = "fk_restaurante"))
	private Restaurante restaurante;

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass () != o.getClass ()) return false;
		Produto produto = (Produto) o;
		return Objects.equals (id, produto.id);
	}

	@Override
	public int hashCode () {
		return Objects.hash (id);
	}
}
