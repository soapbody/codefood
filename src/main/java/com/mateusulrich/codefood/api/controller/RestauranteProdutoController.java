package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.assembler.ProdutoMapper;
import com.mateusulrich.codefood.api.model.ProdutoDTO;
import com.mateusulrich.codefood.api.model.ProdutoMinDTO;
import com.mateusulrich.codefood.domain.model.Produto;
import com.mateusulrich.codefood.domain.model.Restaurante;
import com.mateusulrich.codefood.domain.repository.ProdutoRepository;
import com.mateusulrich.codefood.domain.service.CadastroProdutoService;
import com.mateusulrich.codefood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	@Autowired
	private ProdutoMapper produtoMapper;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@GetMapping
	public List<ProdutoMinDTO> listarProdutos(@PathVariable Long restauranteId,
											  @RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.buscarRestaurante (restauranteId);
		List<Produto> produtos;
		if (incluirInativos){
			produtos = produtoRepository.findByRestaurante (restaurante);
		}else {
			produtos = produtoRepository.findAtivosByRestaurante (restaurante);
		}
		return produtoMapper.toCollectionDTO (produtos);

	}
	@GetMapping("/{produtoId}")
	public ProdutoMinDTO buscarProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarProdutoPorRestaurante(restauranteId, produtoId);
		return produtoMapper.toMinDto (produto);
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoDTO produtoDTO) {
		Restaurante restaurante = cadastroRestaurante.buscarRestaurante (restauranteId);
		Produto produto = produtoMapper.toDomainObject (produtoDTO);
		produto.setRestaurante (restaurante);
		produto = cadastroProdutoService.salvar (produto);
		return produtoMapper.toDTO (produto);
	}

	@PutMapping ("/{produtoId}")
	public ProdutoDTO atualizar (@PathVariable Long restauranteId, Long produtoId,
										@RequestBody @Valid ProdutoDTO produtoDTO) {
		Produto produtoAtual= cadastroProdutoService.buscarProdutoPorRestaurante (restauranteId, produtoId);
		produtoMapper.copyToDomainObject (produtoDTO, produtoAtual);
		return produtoMapper.toDTO (cadastroProdutoService.salvar (produtoAtual));

	}
}
