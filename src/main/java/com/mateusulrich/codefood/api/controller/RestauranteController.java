package com.mateusulrich.codefood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateusulrich.codefood.api.assembler.RestauranteMapper;
import com.mateusulrich.codefood.api.model.RestauranteDTO;
import com.mateusulrich.codefood.api.model.input.RestauranteInput;
import com.mateusulrich.codefood.core.validation.ValidacaoException;
import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.repository.RestauranteRepository;
import com.mateusulrich.codefood.domain.service.CadastroRestauranteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private SmartValidator validator;
	@Autowired
	private RestauranteMapper restauranteMapper;

	@GetMapping
	public List<RestauranteDTO> listar() {
		return restauranteMapper.toCollectionDTO (restauranteRepository.findAll ());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable Long restauranteId) {
		 Restaurante restaurante = cadastroRestaurante.buscarRestaurante(restauranteId);
		 return restauranteMapper.toDTO (restaurante);
	}
	@GetMapping
	public List<RestauranteDTO> buscarTodos() {
		List<Restaurante> restaurantes = restauranteRepository.findAll ();
		return restauranteMapper.toCollectionDTO (restaurantes);
	}
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteMapper.toDomainObject (restauranteInput);
			return restauranteMapper.toDTO (cadastroRestaurante.salvar (restaurante));
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException (e.getMessage ());
		}

	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@Valid @PathVariable Long restauranteId,
									   @RequestBody RestauranteInput restauranteInput) {

		try {
			//Restaurante restaurante = restauranteMapper.toDomainObject (restauranteInput);
			Restaurante restauranteAtual = cadastroRestaurante.buscarRestaurante (restauranteId);
			restauranteMapper.copyToDomainObject (restauranteInput, restauranteAtual);
			//BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco");
			return restauranteMapper.toDTO (cadastroRestaurante.salvar(restauranteAtual));
				}catch (EntidadeNaoEncontradaException e) {
					throw new NegocioException (e.getMessage ());
				}

	}

	@PatchMapping("/{restauranteId}")
	public RestauranteDTO atualizarParcial(@Valid @PathVariable Long restauranteId,
										@RequestBody Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarRestaurante (restauranteId);
		merge(campos, restauranteAtual, request);
		RestauranteInput restauranteInput = new RestauranteInput ();
		BeanUtils.copyProperties (restauranteAtual, restauranteInput);

		validate(restauranteAtual, "restaurante");


		return atualizar(restauranteId, restauranteInput);
	}
	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult (restaurante, objectName);
		validator.validate (restaurante, bindingResult);

		if (bindingResult.hasErrors ()) {
			throw new ValidacaoException (bindingResult);
		}

	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest (request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure (DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		}catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause (e);
			throw new HttpMessageNotReadableException (e.getMessage (), rootCause, serverHttpRequest);
		}

	}


}
