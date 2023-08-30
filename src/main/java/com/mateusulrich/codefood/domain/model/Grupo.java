package com.mateusulrich.codefood.domain.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Grupo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;
	private String nome;

	@ManyToMany
	@JoinTable (
			name = "grupo_permissao",
			joinColumns = @JoinColumn(name = "grupo_id"),
			inverseJoinColumns = @JoinColumn(name = "permissao_id")
	)
	private List<Permissao> permissoes;

	public boolean removerPermissao(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	public boolean inserirPermissao(Permissao permissao) {
		return getPermissoes().add (permissao);
	}

}
