package com.dbc.consumer.service;

import com.dbc.consumer.dto.EmailDTO;
import com.dbc.consumer.dto.LogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailsender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;
    private final LogService logService;


    public EmailDTO enviaEmail(EmailDTO enviaEmailDTO) throws MessagingException, IOException, TemplateException, TemplateException {
        try {
            MimeMessage mimeMessage = emailsender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(remetente);
            helper.setTo(enviaEmailDTO.getDestinatario());
            helper.setSubject(enviaEmailDTO.getAssunto());
            helper.setText(enviaEmailDTO.getTexto(), true);
            emailsender.send(mimeMessage);
            logService.emailComSucesso();
            return enviaEmailDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logService.emailSemSucesso();
        }
        return enviaEmailDTO;
    }
}
