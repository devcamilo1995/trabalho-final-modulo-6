package com.dbc.trabalhovemser.kafka;


import com.dbc.trabalhovemser.dto.EmailDTO;
import com.dbc.trabalhovemser.entity.UsuarioEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EnviarKafka {
    private final Producer producer;
    private final Configuration configuration;

    public void enviarKafka(UsuarioEntity usuarioEntity, String templateHtml) throws MessagingException, IOException, TemplateException {
        Template template = configuration.getTemplate(templateHtml);
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", usuarioEntity.getNome());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Envio de mensagem!");
        emailDTO.setDestinatario(usuarioEntity.getEmail());
        emailDTO.setTexto(html);
        producer.sendMessageDTO(emailDTO);
    }
}