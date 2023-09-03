package com.mateusulrich.codefood.core.storage;

import com.mateusulrich.codefood.domain.service.FotoStorageService;
import com.mateusulrich.codefood.infrastructure.storage.AmazonS3StorageService;
import com.mateusulrich.codefood.infrastructure.storage.LocalFotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Configuration
public class StorageConfig {
	@Autowired
	private StorageProperties storageProperties;
	@Bean
	public AmazonS3 amazonS3() {
		var credentials = new BasicAWSCredentials (
				storageProperties.getS3 ().getIdChaveAcesso (),
				storageProperties.getS3 ().getChaveAcessoSecreta ());

		return AmazonS3ClientBuilder.standard ()
				.withCredentials (new AWSStaticCredentialsProvider (credentials))
				.withRegion (storageProperties.getS3 ().getRegiao ())
				.build ();
	}
	@Bean
	public FotoStorageService fotoStorageService() {
		if (StorageProperties.TipoStorage.S3.equals (storageProperties.getTipo ()))
		{
			return new AmazonS3StorageService ();
		} else return new LocalFotoStorageService ();
	}

}
