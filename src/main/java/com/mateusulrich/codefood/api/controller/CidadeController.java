package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.CidadeMapper;
import com.mateusulrich.codefood.api.model.CidadeDTO;
import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.repository.CidadeRepository;
import com.mateusulrich.codefood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private CidadeMapper cidadeMapper;


	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<CidadeDTO> listar() {
		return cidadeMapper.toCollectionDTO (cidadeRepository.findAll ());
	}

	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId) {
		return cidadeMapper.toDTO (cidadeService.buscarCidadePorId (cidadeId));
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public CidadeDTO adicionar(@Valid @RequestBody CidadeDTO cidadeDTO) {
		try {
			Cidade cidade = cidadeMapper.toDomainObject (cidadeDTO);
			return cidadeMapper.toDTO (cidadeService.salvar (cidade));
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException (e.getMessage ());
		}


	}

	@PutMapping ("/{cidadeId}")
	public CidadeDTO atualizar(@Valid @PathVariable Long cidadeId,
									   @RequestBody CidadeDTO cidadeDTO) {
				try {
					Cidade cidadeAtual = cidadeService.buscarCidadePorId (cidadeId);
					//BeanUtils.copyProperties(cidade, cidadeAtual, "id");
					cidadeMapper.copyToDomainObject (cidadeDTO, cidadeAtual);
					return cidadeMapper.toDTO (cadastroCidade.salvar (cidadeAtual));
				}catch (EntidadeNaoEncontradaException e) {
					throw new NegocioException (e.getMessage ());
				}

	}
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		 cidadeService.excluir (cidadeId);
	}


}
