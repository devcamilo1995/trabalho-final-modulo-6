package com.dbc.trabalhovemser.service;


import com.dbc.trabalhovemser.dto.*;
import com.dbc.trabalhovemser.entity.UsuarioEntity;
import com.dbc.trabalhovemser.kafka.Producer;
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
    private final Producer producer;
    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;


    public UsuarioDTO enviarCadastroUsuario(UsuarioDTO usuarioDTO) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(remetente);
        helper.setTo(usuarioDTO.getEmail());
        helper.setSubject("Usuario cadastrado");
        Template template = configuration.getTemplate("usuario-templates.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", usuarioDTO.getNome());
        dados.put("remetente", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        helper.setText(html, true);
        emailSender.send(mimeMessage);
        return usuarioDTO;
    }

    public ReservaDTO enviarCadastroReserva(ReservaDTO reservaDTO) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(remetente);
        helper.setTo(reservaDTO.getUsuarioDTO().getEmail());
        helper.setSubject("Reserva cadastrada");
        Template template = configuration.getTemplate("reserva-templates.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", reservaDTO.getUsuarioDTO().getNome());
        dados.put("hotel", reservaDTO.getHoteisDTO().getNomeHotel());
        dados.put("quarto", reservaDTO.getQuartosDTO().getNumeroQuarto());
        dados.put("descricao", reservaDTO.getQuartosDTO().getDescricao());
        dados.put("diaria", reservaDTO.getQuartosDTO().getValorDiaria());
        dados.put("remetente", remetente);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        helper.setText(html, true);
        emailSender.send(mimeMessage);
        return reservaDTO;
    }

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

