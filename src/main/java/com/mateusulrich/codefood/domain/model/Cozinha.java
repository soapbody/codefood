package com.mateusulrich.codefood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateusulrich.codefood.core.validation.Groups;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<> ();

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
