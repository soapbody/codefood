package com.mateusulrich.codefood.controller;

import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.repository.CozinhaRepository;
import com.mateusulrich.codefood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll ();
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return cadastroCozinha.buscarCozinha (cozinhaId);
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public Cozinha adicionar(@Valid @RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping ("/{cozinhaId}")
	public Cozinha atualizar(@Valid @PathVariable Long cozinhaId,
							 @RequestBody Cozinha cozinha) {

		Cozinha cozinhaAtual = cadastroCozinha.buscarCozinha (cozinhaId);
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return cadastroCozinha.salvar(cozinhaAtual);
	}

	@DeleteMapping ("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir (cozinhaId);
	}

}
