package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.PedidoDTO;
import com.mateusulrich.codefood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
	@Autowired
	private ModelMapper modelMapper;

	public PedidoDTO toDTO (Pedido pedido) {
		return modelMapper.map (pedido, PedidoDTO.class);
	}
}
