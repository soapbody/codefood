package com.mateusulrich.codefood.core.modelmapper;

import com.mateusulrich.codefood.api.model.EnderecoDTO;
import com.mateusulrich.codefood.api.model.input.ItemPedidoImput;
import com.mateusulrich.codefood.domain.model.Endereco;
import com.mateusulrich.codefood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper ();
		//modelMapper.createTypeMap (Restaurante.class, RestauranteDTO.class)
		//		.addMapping (Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		modelMapper.createTypeMap (ItemPedidoImput.class, ItemPedido.class)
				.addMappings (m -> m.skip (ItemPedido::setId));

		var enderecoToEnderecoDTOTypeMap = modelMapper.createTypeMap (Endereco.class, EnderecoDTO.class);

		enderecoToEnderecoDTOTypeMap.<String>addMapping (
				enderecoSrc -> enderecoSrc.getCidade ().getEstado ().getNome (),
				(enderecoDTODest, value) -> enderecoDTODest.getCidade ().setEstado (value)
		);
		return modelMapper;
	}
}
