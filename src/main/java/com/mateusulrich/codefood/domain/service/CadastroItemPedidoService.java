package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.ItemPedidoNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.ItemPedido;
import com.mateusulrich.codefood.domain.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroItemPedidoService {
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Transactional(readOnly = true)
	public ItemPedido buscarItemPedido(Long itemPedidoId) {
		return itemPedidoRepository.findById(itemPedidoId).orElseThrow(() -> new ItemPedidoNaoEncontradoException (itemPedidoId));
	}
	@Transactional(readOnly = true)
	public List<ItemPedido> findAllById(Long itemPedidoId) {
		return itemPedidoRepository.findAllById (itemPedidoId);
	}


}
