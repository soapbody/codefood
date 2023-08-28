package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.RestauranteDTO;
import com.mateusulrich.codefood.api.model.input.RestauranteInput;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RestauranteMapper {
	@Autowired
	private ModelMapper modelMapper;

	public RestauranteDTO toDTO (Restaurante restaurante) {
		return modelMapper.map (restaurante, RestauranteDTO.class);
	}
	public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
		return restaurantes.stream ()
				.map (restaurante -> toDTO (restaurante))
				.collect(Collectors.toList());
	}
	public Restaurante toDomainObject (RestauranteInput restauranteInput) {
		return modelMapper.map (restauranteInput, Restaurante.class);
	}
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// Para evitar uma exception hibernate.HibernateException: identifier of an instance of
		//com.mateusulrich.codefood.domain.model.Cozinha was altered from 1 to 2.
		restaurante.setCozinha (new Cozinha ());
		if (restaurante.getEndereco () != null)
			restaurante.getEndereco ().setCidade (new Cidade ());
		modelMapper.map (restauranteInput, restaurante);
	}
}
