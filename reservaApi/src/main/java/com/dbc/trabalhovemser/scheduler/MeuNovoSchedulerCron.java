package com.dbc.trabalhovemser.scheduler;

import com.dbc.trabalhovemser.entity.UsuarioEntity;
import com.dbc.trabalhovemser.kafka.EnviarKafka;
import com.dbc.trabalhovemser.repository.UsuarioRepository;
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
public class MeuNovoSchedulerCron {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("HH:mm:ss");
    private final UsuarioRepository usuarioRepository;
    private final EnviarKafka enviarKafka;

//    @Scheduled(cron = "0 18-21 15,20 * * *", zone = "GMT-3")
//    public void meuPrimeiroScheduler() throws InterruptedException {
////        Thread.sleep(1000);
//        log.info("{}", dateFormat.format(new Date()));
//    }
//
//        @Scheduled(cron = "0 06-12 17 * * *")
//        public void scheduleEmail() throws InterruptedException, MessagingException, TemplateException, IOException {
//        List<PessoaEntity> entity = pessoaRepository.procurarPessoasComEnderecoNull();
//        for (PessoaEntity pessoa : entity) {
//            emailService.enviarCadastroReserva(pessoa, "email-template.ftl");
//        }


    @Scheduled(cron = "0 23 16 * * *")
    public void meuPrimeiroScheduler() throws InterruptedException, MessagingException, TemplateException, IOException {
        List<UsuarioEntity> pessoas = usuarioRepository.findAll();
            for (UsuarioEntity pessoa : pessoas) {
                enviarKafka.enviarKafka(pessoa, "email-template.ftl");
            }
        }
    }
