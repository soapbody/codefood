package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.CozinhaDTO;
import com.mateusulrich.codefood.api.model.input.CozinhaInput;
import com.mateusulrich.codefood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CozinhaMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaDTO toDTO (Cozinha cozinha) {
		return modelMapper.map (cozinha, CozinhaDTO.class);
	}
	public List<CozinhaDTO> toCollectionDTO(List<Cozinha> cozinhas) {
		return cozinhas.stream ()
				.map (cozinha -> toDTO (cozinha))
				.collect(Collectors.toList());
	}
	public Cozinha toDomainObject (CozinhaInput cozinhaInput) {
		return modelMapper.map (cozinhaInput, Cozinha.class);
	}
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		modelMapper.map (cozinhaInput, cozinha);
	}
}
