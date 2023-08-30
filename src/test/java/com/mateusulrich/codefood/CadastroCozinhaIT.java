package com.mateusulrich.codefood;

import com.mateusulrich.codefood.domain.exception.CozinhaNãoEncontradaException;
import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.RestauranteNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.repository.RestauranteRepository;
import com.mateusulrich.codefood.domain.service.CadastroCozinhaService;
import com.mateusulrich.codefood.domain.service.CadastroRestauranteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolationException;
import java.math.BigDecimal;

@SpringBootTest
class CadastroCozinhaIT {
		@Autowired
		private CadastroCozinhaService cadastroCozinhaService;

		@Autowired
		private CadastroRestauranteService cadastroRestauranteService;

		@Autowired
		private RestauranteRepository restauranteRepository;
		@Test
		public void testarCadastroCozinhaComSucesso() {
			// cenário
			Cozinha novaCozinha = new Cozinha ();
			novaCozinha.setNome ("Chinesa");
			novaCozinha.setDescricao ("Cozinha chinesa");

			// ação
			novaCozinha = cadastroCozinhaService.salvar (novaCozinha);

			// validação
			Assertions.assertEquals (true, novaCozinha != null);
			Assertions.assertEquals (true, novaCozinha.getId () != null);

		}
		@Test
		public void testarCadastroCozinhaSemNome() {
			Cozinha novaCozinha = new Cozinha ();
			novaCozinha.setNome (null);
			novaCozinha.setDescricao ("adadasdasda");

			Assertions.assertThrows (ConstraintViolationException.class, () -> {
				cadastroCozinhaService.salvar (novaCozinha);
			});

		}
	@Test
	public void deve_falhar_quando_excluir_cozinha_em_uso() {
		Restaurante novoRestaurante = new Restaurante();
		Cozinha novaCozinha = new Cozinha();

		novaCozinha.setNome("cozinha teste em uso");
		novaCozinha.setDescricao("Cozinha teste em uso");
		cadastroCozinhaService.salvar(novaCozinha);

		novoRestaurante.setNome("restaurante teste em uso");
		novoRestaurante.setTaxaFrete(BigDecimal.valueOf(12.0));
		novoRestaurante.setCozinha(novaCozinha);
		cadastroRestauranteService.salvar(novoRestaurante);

		Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinhaService.excluir(novaCozinha.getId());
		});

		restauranteRepository.deleteById(novoRestaurante.getId());
		Assertions.assertThrows(RestauranteNaoEncontradoException.class, () -> {
			cadastroRestauranteService.buscarRestaurante (novoRestaurante.getId ());
		});

		cadastroCozinhaService.excluir(novaCozinha.getId());
		Assertions.assertThrows(CozinhaNãoEncontradaException.class, () -> {
			cadastroCozinhaService.buscarCozinha (novaCozinha.getId());
		});
	}

	@Test
	public void deve_falhar_quando_excluir_cozinha_inexistente() {
		Assertions.assertThrows (EntidadeNaoEncontradaException.class, () -> {
			cadastroCozinhaService.excluir (9999999L);
		});

	}




}
