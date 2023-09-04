package com.mateusulrich.codefood.infrastructure.service.email;

import com.mateusulrich.codefood.core.email.EmailProperties;
import com.mateusulrich.codefood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
public class SmtpEnvioEmailService implements EnvioEmailService {
	@Autowired
	protected JavaMailSender mailSender;
	@Autowired
	protected EmailProperties emailProperties;
	@Autowired
	protected Configuration freeMarkerConfig;

	@Override
	public void enviar (Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = criarMimeMessage(mensagem);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}

	protected String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freeMarkerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template,mensagem.getVariaveis());
		} catch (IOException | TemplateException e) {
			throw new EmailException("Não foi possível montar o Template do E-mail", e);
		}

	}
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		String corpo = processarTemplate(mensagem);

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		helper.setText(corpo, true);

		return mimeMessage;
	}
}
