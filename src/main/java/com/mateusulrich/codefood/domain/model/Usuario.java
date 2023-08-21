package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	@Column(nullable = false, columnDefinition = "timestamp")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime creationDate;

	@ManyToMany
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"),
			foreignKey = @ForeignKey(name = "fk_usuario"),
			inverseForeignKey = @ForeignKey(name = "fk_grupo"))
	private List<Grupo> grupos = new ArrayList<> ();
}
