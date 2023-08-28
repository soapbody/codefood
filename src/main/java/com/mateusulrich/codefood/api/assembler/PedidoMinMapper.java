package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.PedidoMinDTO;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMinMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoMinDTO toDTO (Pedido pedido) {
		return modelMapper.map (pedido, PedidoMinDTO.class);
	}
	public List<PedidoMinDTO> toCollectionDTO(List<Pedido> pedidos) {
		return pedidos.stream ()
				.map (pedido -> toDTO (pedido))
				.collect(Collectors.toList());
	}
	public Pedido toDomainObject (PedidoMinDTO pedidoMinDTO) {
		return modelMapper.map (pedidoMinDTO, Pedido.class);
	}
	public void copyToDomainObject(PedidoMinDTO pedidoMinDTO, Pedido pedido) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		pedido.setRestaurante (new Restaurante ());
		if (pedido.getEnderecoEntrega () != null)
			pedido.getEnderecoEntrega ().setCidade (new Cidade ());
		modelMapper.map (pedidoMinDTO, pedido);
	}
}
