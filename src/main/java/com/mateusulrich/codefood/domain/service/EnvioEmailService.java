package com.mateusulrich.codefood.domain.service;

import lombok.*;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService{
	void enviar(Mensagem mensagem);
	@Getter
	@Builder
	class Mensagem{
		@Singular
		private Set<String> destinatarios;
		@NonNull
		private String assunto;
		@NonNull
		private String corpo;
		@Singular("variavel")
		private Map<String, Object> variaveis;
	}
}
