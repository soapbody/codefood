package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.NegocioException;
import com.mateusulrich.codefood.domain.exception.PedidoNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.*;
import com.mateusulrich.codefood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private CadastroUsuarioService usuarioService;
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CadastroItemPedidoService itemPedidoService;

	public Pedido buscarPedido(String codigo) {
		return pedidoRepository.findByCodigo (codigo).orElseThrow(() -> new PedidoNaoEncontradoException (codigo));
	}
	public List<Pedido> buscarTodosPedido(Specification<Pedido> pedidoSpecification) {
		return pedidoRepository.findAll ();
	}
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido (pedido);
		validarItens (pedido);
		pedido.setTaxaFrete (pedido.getRestaurante ().getTaxaFrete ());
		pedido.calcularValorTotal ();
		return pedidoRepository.save(pedido);
	}
	public void validarPedido(Pedido pedido) {
		Restaurante restaurante = restauranteService.buscarRestaurante (pedido.getRestaurante ().getId ());
		Usuario cliente = usuarioService.buscarUsuario (pedido.getCliente ().getId ());
		FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamento (pedido.getFormaPagamento ().getId ());
		Cidade cidade = cidadeService.buscarCidadePorId (pedido.getEnderecoEntrega ().getCidade ().getId ());
		pedido.getEnderecoEntrega ().setCidade (cidade);
		pedido.setCliente (cliente);
		pedido.setRestaurante (restaurante);
		pedido.setFormaPagamento (formaPagamento);
		if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException (String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
					formaPagamento.getDescricao()));
		}
	}
	public void validarItens(Pedido pedido) {
		pedido.getItens ().forEach (itemPedido -> {
			Produto produto = produtoService.buscarProdutoPorRestaurante (pedido.getRestaurante ().getId (), itemPedido.getProduto ().getId ());
			itemPedido.setProduto (produto);
			itemPedido.setPrecoUnitario (produto.getPreco ());
			//itemPedido.setPrecoTotal (produto.getPreco ().multiply (new BigDecimal (itemPedido.getQuantidade ())));
			itemPedido.setPedido (pedido);
		});
	}



}
