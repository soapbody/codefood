package com.mateusulrich.codefood.domain.repository;

import com.mateusulrich.codefood.domain.model.ItemPedido;

import java.util.List;

public interface ItemPedidoRepository extends CustomJpaRepository<ItemPedido, Long>{
	List<ItemPedido> findAllById (Long itemPedidoId);
}
