package com.mateusulrich.codefood.infrastructure.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mateusulrich.codefood.core.storage.StorageProperties;
import com.mateusulrich.codefood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class AmazonS3StorageService implements FotoStorageService {
	@Autowired
	private AmazonS3 amazonS3;
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar (NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo (novaFoto.getNomeArquivo ());
			var objectMetadata = new ObjectMetadata ();
			objectMetadata.setContentType (novaFoto.getContentType());
			var putObjectRequest = new PutObjectRequest (
					storageProperties.getS3 ().getBucket (),
					caminhoArquivo,
					novaFoto.getInputStream (), objectMetadata)
					.withCannedAcl (CannedAccessControlList.PublicRead);


			amazonS3.putObject (putObjectRequest);

		}catch (Exception e) {
			throw new StorageException ("Não foi possível enviar o arquivo para Amazon S3.", e);
		}

	}

	@Override
	public void remover (String nomeArquivo) {
		try {
			var deleteObjectRequest = new DeleteObjectRequest (
					storageProperties.getS3 ().getBucket (),
					getCaminhoArquivo (nomeArquivo));
			amazonS3.deleteObject (deleteObjectRequest);
		}catch (Exception e) {
			throw new StorageException ("Não foi possível deletar o arquivo da Amazon S3.", e);
		}

	}

	@Override
	public FotoRecuperada recuperar (String nomeArquivo) {
		try {
			String caminhoArquivo = getCaminhoArquivo (nomeArquivo);
			URL url = amazonS3.getUrl (storageProperties.getS3 ().getBucket (), caminhoArquivo);
			return FotoRecuperada.builder ()
					.url (url.toString ())
					.build ();

		}catch (Exception e) {
			throw new StorageException ("Não foi possível recuperar o arquivo da Amazon S3.", e);
		}

	}
	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3 ().getDiretorioFotos (), nomeArquivo);
	}
}
