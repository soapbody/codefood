package com.mateusulrich.codefood.infrastructure.storage;

import com.mateusulrich.codefood.core.storage.StorageProperties;
import com.mateusulrich.codefood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class LocalFotoStorageService implements FotoStorageService {
	@Autowired
	private StorageProperties storageProperties;
	@Override
	public void armazenar (NovaFoto novaFoto) {
		try {
			Path arquivoPath = getArquivoPath (novaFoto.getNomeArquivo ());
					FileCopyUtils.copy (novaFoto.getInputStream (), Files.newOutputStream (arquivoPath));
		} catch (IOException e) {
			throw new StorageException ("Não foi possível armazenar o Arquivo.", e);
		}

	}

	@Override
	public void remover (String nomeArquivo) {
		Path arquivoPath = getArquivoPath (nomeArquivo);
		try {
			Files.deleteIfExists (arquivoPath);
		} catch (IOException e) {
			throw new StorageException ("Não foi possível deletar o Arquivo.", e);
		}

	}

	@Override
	public FotoRecuperada recuperar (String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath (nomeArquivo);
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder ()
					.inputStream (Files.newInputStream (arquivoPath))
					.build ();
			return fotoRecuperada;
		} catch (IOException e) {
			throw new StorageException ("Não foi possível recuperar arquivo.",e);
		}
	}

	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal ().getDiretorioFotos ().resolve (Path.of (nomeArquivo));

	}
}
