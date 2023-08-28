package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.input.PedidoInput;
import com.mateusulrich.codefood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputMapper {
	@Autowired
	private ModelMapper modelMapper;

	public PedidoInput toDTO (Pedido pedido) {
		return modelMapper.map (pedido, PedidoInput.class);
	}
	public Pedido toDomainObject (PedidoInput pedidoInput) {
		return modelMapper.map (pedidoInput, Pedido.class);
	}
	public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
		modelMapper.map (pedidoInput, pedido);
	}
}
