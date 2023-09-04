package com.mateusulrich.codefood.core.email;

import com.mateusulrich.codefood.core.storage.StorageProperties;
import com.mateusulrich.codefood.domain.service.EnvioEmailService;
import com.mateusulrich.codefood.domain.service.FotoStorageService;
import com.mateusulrich.codefood.infrastructure.service.email.FakeEnvioEmailService;
import com.mateusulrich.codefood.infrastructure.service.email.SmtpEnvioEmailService;
import com.mateusulrich.codefood.infrastructure.storage.AmazonS3StorageService;
import com.mateusulrich.codefood.infrastructure.storage.LocalFotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        if (EmailProperties.TipoEmail.PROD.equals (emailProperties.getTipoEmail ()))
        {
            return new SmtpEnvioEmailService();
        }
        else return new FakeEnvioEmailService();
    }
}
