package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.CozinhaMapper;
import com.mateusulrich.codefood.api.model.CozinhaDTO;
import com.mateusulrich.codefood.api.model.input.CozinhaInput;
import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.repository.CozinhaRepository;
import com.mateusulrich.codefood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	@Autowired
	private CozinhaMapper cozinhaMapper;

	@GetMapping
	public Page<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhaPage = cozinhaRepository.findAll (pageable);
		List<CozinhaDTO> cozinhaDTOS = cozinhaMapper.toCollectionDTO (cozinhaPage.getContent ());
		Page<CozinhaDTO> cozinhaDTOPage = new PageImpl<> (cozinhaDTOS, pageable, cozinhaPage.getTotalElements ());
		return cozinhaDTOPage;
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaDTO buscar(@PathVariable Long id) {
		return cozinhaMapper.toDTO (cadastroCozinha.buscarCozinha (id));
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public CozinhaDTO adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
		return cozinhaMapper.toDTO (cozinhaMapper.toDomainObject (cozinhaInput));
	}

	@PutMapping ("/{cozinhaId}")
	public CozinhaDTO atualizar(@Valid @PathVariable Long cozinhaId,
							 @RequestBody CozinhaInput cozinhaInput) {

		Cozinha cozinhaAtual = cadastroCozinha.buscarCozinha (cozinhaId);
			//BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaMapper.copyToDomainObject (cozinhaInput, cozinhaAtual);
			return cozinhaMapper.toDTO (cadastroCozinha.salvar(cozinhaAtual));
	}

	@DeleteMapping ("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir (cozinhaId);
	}

}
