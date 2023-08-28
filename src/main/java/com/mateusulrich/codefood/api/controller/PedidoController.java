package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.PedidoInputMapper;
import com.mateusulrich.codefood.api.assembler.PedidoMapper;
import com.mateusulrich.codefood.api.assembler.PedidoMinMapper;
import com.mateusulrich.codefood.api.model.PedidoDTO;
import com.mateusulrich.codefood.api.model.PedidoMinDTO;
import com.mateusulrich.codefood.api.model.input.PedidoInput;
import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.model.Pedido;
import com.mateusulrich.codefood.domain.model.Usuario;
import com.mateusulrich.codefood.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private CadastroPedidoService cadastroPedidoService;
	@Autowired
	private PedidoInputMapper pedidoInputMapper;
	@Autowired
	private PedidoMinMapper pedidoMinMapper;
	@Autowired
	private PedidoMapper pedidoMapper;

	@GetMapping("/{codigo}")
	public PedidoDTO buscar(@PathVariable String codigo) {
		return pedidoMapper.toDTO (cadastroPedidoService.buscarPedido (codigo));
	}
	@GetMapping
	public List<PedidoMinDTO> buscarTodos() {
		return pedidoMinMapper.toCollectionDTO (cadastroPedidoService.buscarTodosPedido());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoInput salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido pedido = pedidoInputMapper.toDomainObject (pedidoInput);
			pedido.setCliente(new Usuario ());
			pedido.getCliente().setId(1L);
			pedido = cadastroPedidoService.emitir(pedido);
			return pedidoInputMapper.toDTO (pedido);
		}catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException (e.getMessage ());
		}
	}




}
