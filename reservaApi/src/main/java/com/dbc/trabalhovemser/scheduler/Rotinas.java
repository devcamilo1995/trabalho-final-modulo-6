package com.dbc.trabalhovemser.scheduler;

import com.dbc.trabalhovemser.entity.UsuarioEntity;
import com.dbc.trabalhovemser.repository.UsuarioRepository;
import com.dbc.trabalhovemser.service.EmailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Rotinas {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("HH:mm:ss");
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;



    @Scheduled(cron = "0 08-09 17 * * *")
//    @Scheduled(fixedDelay = 300000)
    public void meuPrimeiroScheduler() throws InterruptedException, MessagingException, TemplateException, IOException {
        List<UsuarioEntity> pessoas = usuarioRepository.findAll();
            for (UsuarioEntity pessoa : pessoas) {
                emailService.enviarKafka(pessoa, "email-template.ftl");
            }
        }
    }
