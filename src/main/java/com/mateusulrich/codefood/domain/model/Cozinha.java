package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.core.validation.Groups;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@JsonRootName("cozinha")
@Getter
@Setter
@Entity
@Table(name = "cozinha")
public class Cozinha {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull (groups = Groups.CozinhaId.class)
	private Long id;

	@NotBlank
	@Column (nullable = false)
	private String nome;

	@NotBlank
	@Column (nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<> ();

	public Cozinha () {
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass () != o.getClass ()) return false;
		Cozinha cozinha = (Cozinha) o;
		return Objects.equals (id, cozinha.id);
	}

	@Override
	public int hashCode () {
		return Objects.hash (id);
	}
}
