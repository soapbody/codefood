package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.FormaPagamentoDTO;
import com.mateusulrich.codefood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class FormaPagamentoMapper {

	@Autowired
	private ModelMapper modelMapper;
	public FormaPagamentoDTO toDTO (FormaPagamento formaPagamento) {
		return modelMapper.map (formaPagamento, FormaPagamentoDTO.class);
	}
	public List<FormaPagamentoDTO> toCollectionDTO(Collection<FormaPagamento> formaPagamentos) {
		return formaPagamentos.stream ()
				.map (formaPagamento -> toDTO (formaPagamento))
				.collect(Collectors.toList());
	}
	public FormaPagamento toDomainObject (FormaPagamentoDTO formaPagamentoDTO) {
		return modelMapper.map (formaPagamentoDTO, FormaPagamento.class);
	}
	public void copyToDomainObject(FormaPagamentoDTO formaPagamentoDTO, FormaPagamento formaPagamento) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		modelMapper.map (formaPagamentoDTO, formaPagamento);
	}
}
