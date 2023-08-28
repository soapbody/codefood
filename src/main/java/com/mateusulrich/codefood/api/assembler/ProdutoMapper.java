package com.mateusulrich.codefood.api.assembler;

import com.mateusulrich.codefood.api.model.ProdutoDTO;
import com.mateusulrich.codefood.api.model.ProdutoMinDTO;
import com.mateusulrich.codefood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoMinDTO toMinDto (Produto produto) {
		return modelMapper.map (produto, ProdutoMinDTO.class);
	}
	public ProdutoDTO toDTO(Produto produto) {
		return modelMapper.map (produto, ProdutoDTO.class);
	}
	public List<ProdutoMinDTO> toCollectionDTO(Collection<Produto> produtos) {
		return produtos.stream ()
				.map (produto -> toMinDto (produto))
				.collect(Collectors.toList());
	}
	public Produto toDomainObject (ProdutoDTO produtoDTO) {
		return modelMapper.map (produtoDTO, Produto.class);
	}
	public void copyToDomainObject(ProdutoDTO produtoDTO, Produto produto) {;
		modelMapper.map (produtoDTO, produto);
	}
}
