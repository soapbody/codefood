package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.CidadeDTO;
import com.mateusulrich.codefood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CidadeMapper {
	@Autowired
	private ModelMapper modelMapper;
	public CidadeDTO toDTO (Cidade cidade) {
		return modelMapper.map (cidade, CidadeDTO.class);
	}
	public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades) {
		return cidades.stream ()
				.map (cidade -> toDTO (cidade))
				.collect(Collectors.toList());
	}
	public Cidade toDomainObject (CidadeDTO cidadeDTO) {
		return modelMapper.map (cidadeDTO, Cidade.class);
	}
	public void copyToDomainObject(CidadeDTO CidadeDTO, Cidade cidade) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		modelMapper.map (CidadeDTO, cidade);
	}
}
