package com.mateusulrich.codefood.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter

public class Usuario {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;

	private String nome;
	private String email;
	private String senha;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "timestamp with time zone")
	private OffsetDateTime creationDate;

	@ManyToMany
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"),
			foreignKey = @ForeignKey(name = "fk_usuario"),
			inverseForeignKey = @ForeignKey(name = "fk_grupo"))
	private Set<Grupo> grupos = new HashSet<> ();

	public boolean senhaCoincideCom(String senha) {
		return getSenha().equals(senha);
	}
	public boolean senhaNaoCoincideCom(String senha) {
		return !senhaCoincideCom(senha);
	}
	public boolean removerGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	public boolean inserirGrupo(Grupo grupo) {
		return getGrupos().add (grupo);
	}
}
