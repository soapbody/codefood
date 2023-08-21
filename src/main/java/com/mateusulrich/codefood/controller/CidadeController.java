package com.mateusulrich.codefood.controller;

import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.Cidade;
import com.mateusulrich.codefood.domain.repository.CidadeRepository;
import com.mateusulrich.codefood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CidadeRepository cidadeRepository;


	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll ();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cidadeService.buscarCidadePorId (cidadeId);
	}

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public Cidade adicionar(@Valid @RequestBody Cidade cidade) {
		try {
			return cidadeService.salvar (cidade);
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException (e.getMessage ());
		}


	}

	@PutMapping ("/{cidadeId}")
	public Cidade atualizar(@Valid @PathVariable Long cidadeId,
									   @RequestBody Cidade cidade) {
				try {
					Cidade cidadeAtual = cidadeService.buscarCidadePorId (cidadeId);
					BeanUtils.copyProperties(cidade, cidadeAtual, "id");
					return cadastroCidade.salvar (cidadeAtual);
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
