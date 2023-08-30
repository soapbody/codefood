package com.mateusulrich.codefood.domain.model;

import com.mateusulrich.codefood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Estado {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NotNull (groups = Groups.EstadoId.class)
	private Long id;

	@NotBlank
	@Column (nullable = false)
	private String nome;

}
