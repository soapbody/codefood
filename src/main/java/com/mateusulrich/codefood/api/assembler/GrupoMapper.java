package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.GrupoDTO;
import com.mateusulrich.codefood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class GrupoMapper {
	@Autowired
	private ModelMapper modelMapper;

	public GrupoDTO toDTO (Grupo grupo) {
		return modelMapper.map (grupo, GrupoDTO.class);
	}
	public List<GrupoDTO> toCollectionDTO(Collection<Grupo> grupos) {
		return grupos.stream ()
				.map (grupo -> toDTO (grupo))
				.collect(Collectors.toList());
	}
	public Grupo toDomainObject (GrupoDTO grupoDTO) {
		return modelMapper.map (grupoDTO, Grupo.class);
	}
	public void copyToDomainObject(GrupoDTO grupoDTO, Grupo grupo) {;
		modelMapper.map (grupoDTO, grupo);
	}
}
