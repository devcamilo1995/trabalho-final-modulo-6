package com.dbc.consumer.service;

import com.dbc.consumer.dto.EmailDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailsender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;

    public void enviarEmailTemplate(EmailDTO emailDTO) throws MessagingException {
        try {
            MimeMessage mimeMessage = emailsender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(remetente);
            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAssunto());

            Template template = configuration.getTemplate("email-template.html");
            Map<String, Object> dados = new HashMap<>();
            dados.put("mensagem", emailDTO.getTexto());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

            helper.setText(html, true);

            emailsender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
