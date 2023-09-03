package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.FotoProdutoNaoEncontradaException;
import com.mateusulrich.codefood.domain.model.FotoProduto;
import com.mateusulrich.codefood.domain.repository.ProdutoRepository;
import com.mateusulrich.codefood.domain.service.FotoStorageService.NovaFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private FotoStorageService fotoStorageService;
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto, InputStream dadosArquivo) {
		Long restauranteId = fotoProduto.getRestauranteId ();
		Long produtoId = fotoProduto.getProduto ().getId ();
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo (fotoProduto.getNomeArquivo ());
		String nomeArquivoExistente = null;

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById (restauranteId, produtoId);

		if(fotoExistente.isPresent ()) {
			nomeArquivoExistente = fotoExistente.get ().getNomeArquivo ();
			produtoRepository.delete (fotoExistente.get ());
		}
		fotoProduto.setNomeArquivo (nomeNovoArquivo);
		fotoProduto = produtoRepository.save (fotoProduto);
		produtoRepository.flush ();

		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo (fotoProduto.getNomeArquivo ())
				.contentType (fotoProduto.getContentType ())
				.inputStream (dadosArquivo).build ();

		fotoStorageService.SalvarOuSubstituir (nomeArquivoExistente, novaFoto);
		return fotoProduto;
	}
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException (restauranteId, produtoId));
	}
	@Transactional
	public void remover(Long restauranteId, Long produtoId) {
		FotoProduto fotoARemover = buscarOuFalhar(restauranteId, produtoId);
		produtoRepository.delete(fotoARemover);
		fotoStorageService.remover (fotoARemover.getNomeArquivo ());
	}

}
