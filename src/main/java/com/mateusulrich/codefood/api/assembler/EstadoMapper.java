package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.EstadoDTO;
import com.mateusulrich.codefood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class EstadoMapper {
	@Autowired
	private ModelMapper modelMapper;
	public EstadoDTO toDTO (Estado estado) {
		return modelMapper.map (estado, EstadoDTO.class);
	}
	public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
		return estados.stream ()
				.map (estado -> toDTO (estado))
				.collect(Collectors.toList());
	}
	public Estado toDomainObject (EstadoDTO estadoDTO) {
		return modelMapper.map (estadoDTO, Estado.class);
	}
	public void copyToDomainObject(EstadoDTO estadoDTO, Estado estado) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		modelMapper.map (estadoDTO, estado);
	}
}
