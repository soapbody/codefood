package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.RestauranteNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.*;
import com.mateusulrich.codefood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroRestauranteService {
	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadastroCozinhaService cozinhaService;
	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CadastroUsuarioService usuarioService;
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {

		Cozinha cozinha = cozinhaService.buscarCozinha (restaurante.getCozinha().getId());
		Cidade cidade = cidadeService.buscarCidadePorId (restaurante.getEndereco ().getCidade ().getId ());
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save (restaurante);
	}
	public Restaurante buscarRestaurante (Long restauranteId) {
		return restauranteRepository.findById (restauranteId).orElseThrow (() -> new RestauranteNaoEncontradoException (restauranteId));
	}
	@Transactional
	public void desassociarFormaPagamento (Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarFormaPagamento (formaPagamentoId);
		restaurante.removerFormaPagamento (formaPagamento);
	}

	@Transactional
	public void associarFormaPagamento (Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarFormaPagamento (formaPagamentoId);
		restaurante.adicionarFormaPagamento (formaPagamento);
	}
	@Transactional(readOnly = true)
	public List<Produto> buscarProdutosAtivo(Long restauranteId){
		Restaurante restaurante = buscarRestaurante (restauranteId);
		return restaurante.getProdutos().stream()
			.filter(produto -> produto != null && Boolean.TRUE.equals(produto.getAtivo()))
			.collect(Collectors.toList());
	}
	@Transactional
	public void ativar(Long restauranteId){
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.ativar ();
	}
	@Transactional
	public void inativar(Long restauranteId){
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.inativar ();
	}
	@Transactional
	public void abertura (Long restauranteId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.abrirRestaurante ();
	}
	@Transactional
	public void fechamento (Long restauranteId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.fecharRestaurante ();
	}
	@Transactional
	public void associarResponsavel (Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.adicionarResponsavel (usuarioService.buscarUsuario (usuarioId));
	}
	@Transactional
	public void desassociarResponsavel (Long restauranteId, Long usuarioId) {
		Restaurante restaurante = buscarRestaurante (restauranteId);
		restaurante.removerResponsavel (usuarioService.buscarUsuario (usuarioId));
	}
	@Transactional
	public void ativarTodos(List<Long> restaurantesId) {
		restaurantesId.forEach(this::ativar);
	}
	@Transactional
	public void inativarTodos(List<Long> restaurantesId) {
		restaurantesId.forEach(this::inativar);
	}
}
