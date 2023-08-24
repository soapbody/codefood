package com.mateusulrich.codefood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.model.Endereco;
import com.mateusulrich.codefood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

	public JacksonMixinModule () {
		setMixInAnnotation (Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation (Cidade.class, CidadeMixin.class);
		setMixInAnnotation (Endereco.class, EnderecoMixin.class);
		setMixInAnnotation (Cozinha.class, CozinhaMixin.class);
	}
}
