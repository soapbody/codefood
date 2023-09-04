package com.mateusulrich.codefood.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties("codefood.storage")
@Getter
@Setter
public class StorageProperties {
	private Local local = new Local();
	private S3 s3 = new S3();
	private TipoStorage tipo = TipoStorage.LOCAL;

	public enum TipoStorage {
		LOCAL, S3
	}
	@Getter
	@Setter
	public class Local {
		private Path diretorioFotos;
	}
	@Getter
	@Setter
	public class S3 {
		//@Value ("${AWS_ACCESS_KEY_ID}")
		private String idChaveAcesso;
		//@Value("${AWS_SECRET_ACCESS_KEY}")
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}

}
