package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.FormaPagamentoMapper;
import com.mateusulrich.codefood.api.model.FormaPagamentoDTO;
import com.mateusulrich.codefood.domain.model.FormaPagamento;
import com.mateusulrich.codefood.domain.repository.FormaPagamentoRepository;
import com.mateusulrich.codefood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	@Autowired
	private FormaPagamentoMapper formaPagamentoMapper;

	@GetMapping
	public List<FormaPagamentoDTO> listar () {
		return formaPagamentoMapper.toCollectionDTO (formaPagamentoRepository.findAll ());
	}
	@GetMapping ("/{estadoId}")
	public FormaPagamentoDTO buscar (@PathVariable Long id) {
		return formaPagamentoMapper.toDTO (formaPagamentoService.buscarFormaPagamento (id));

	}
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public FormaPagamentoDTO adicionar (@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO) {
		FormaPagamento formaPagamento = formaPagamentoMapper.toDomainObject (formaPagamentoDTO);
		return formaPagamentoMapper.toDTO (formaPagamentoService.salvar (formaPagamento));
	}

	@PutMapping ("/{id}")
	public FormaPagamentoDTO atualizar (@Valid @PathVariable Long id,
								@RequestBody FormaPagamentoDTO formaPagamentoDTO) {
		FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamento (id);
		//BeanUtils.copyProperties (estado, estadoAtual, "id");
		formaPagamentoMapper.copyToDomainObject (formaPagamentoDTO, formaPagamento);
		return formaPagamentoMapper.toDTO (formaPagamentoService.salvar (formaPagamento));

	}
	@DeleteMapping ("/{id}")
	@ResponseStatus (value = HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long id) {
		formaPagamentoService.excluir (id);
	}
}
