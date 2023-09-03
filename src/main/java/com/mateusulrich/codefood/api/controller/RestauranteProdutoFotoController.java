package com.mateusulrich.codefood.api.controller;

import com.mateusulrich.codefood.api.model.FotoProdutoDTO;
import com.mateusulrich.codefood.api.model.input.FotoProdutoInput;
import com.mateusulrich.codefood.domain.exception.EntidadeNaoEncontradaException;
import com.mateusulrich.codefood.domain.model.FotoProduto;
import com.mateusulrich.codefood.domain.model.Produto;
import com.mateusulrich.codefood.domain.service.CadastroProdutoService;
import com.mateusulrich.codefood.domain.service.CatalogoFotoProdutoService;
import com.mateusulrich.codefood.domain.service.FotoStorageService;
import com.mateusulrich.codefood.domain.service.FotoStorageService.FotoRecuperada;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	@Autowired
	private FotoStorageService fotoStorageService;
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
										@PathVariable Long produtoId,
										@Valid FotoProdutoInput fotoProdutoInput) throws IOException {
//		var nomeArquivo = UUID.randomUUID ().toString ()
//				+ "_" + fotoProdutoInput.getArquivo ().getOriginalFilename ();
//		var arquivoFoto = Path.of ("C:\\Users\\MateusLovesJamine\\Downloads\\fotosteste", nomeArquivo);
//
//		System.out.println (fotoProdutoInput.getArquivo ().getContentType ());
//		System.out.println (fotoProdutoInput.getDescricao ());
//		System.out.println (arquivoFoto);
//		try {
//			fotoProdutoInput.getArquivo ().transferTo (arquivoFoto);
//		} catch (IOException e) {
//			throw new RuntimeException (e);
//		}
		Produto produto = cadastroProdutoService.buscarProdutoPorRestaurante (restauranteId, produtoId);
		MultipartFile arquivo = fotoProdutoInput.getArquivo ();
		FotoProduto foto = new FotoProduto (produto, fotoProdutoInput, arquivo);
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar (foto, arquivo.getInputStream ());

		return new FotoProdutoDTO (fotoSalva);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoDTO buscar(@PathVariable Long restauranteId,
						   @PathVariable Long produtoId) {
		return new FotoProdutoDTO (catalogoFotoProdutoService.buscarOuFalhar (restauranteId, produtoId));
	}
	@GetMapping
	public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
														  @PathVariable Long produtoId,
														  @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try{
			FotoProduto foto = catalogoFotoProdutoService.buscarOuFalhar (restauranteId, produtoId);

			MediaType mediaTypeFoto = MediaType.parseMediaType (foto.getContentType ());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes (acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar (foto.getNomeArquivo ());
			if (fotoRecuperada.temUrl ()){
				return ResponseEntity.status (HttpStatus.FOUND)
						.header (HttpHeaders.LOCATION, fotoRecuperada.getUrl ())
						.build ();
			}else {
				return ResponseEntity.ok ()
						.contentType(mediaTypeFoto)
						.body (new InputStreamResource (fotoRecuperada.getInputStream ()));
			}
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound ().build ();
		}

	}
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId,
								 @PathVariable Long produtoId) {
		catalogoFotoProdutoService.remover (restauranteId, produtoId);
	}
	/*
	@GetMapping("/baixar")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Long restauranteId,
											   @PathVariable Long produtoId) throws IOException {
		InputStream inputStream = localFotoStorageService.recuperar (
				catalogoFotoProdutoService.buscarNomeArquivo (restauranteId, produtoId));
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];

		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();
		byte[] byteArray = buffer.toByteArray();

		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.header("Content-Disposition", "attachment; filename=\"nomeDoArquivo.jpg\"")
				.body(byteArray);
	}

	 */
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream ()
				.anyMatch (mediaTypesAceita -> mediaTypesAceita.isCompatibleWith (mediaTypeFoto));
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException (mediaTypesAceitas);
		}
	}
}
