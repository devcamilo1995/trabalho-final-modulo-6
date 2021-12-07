package com.dbc.logapi.scheduler;

import com.dbc.logapi.entity.LogEntity;
import com.dbc.logapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class Rotinas {
    private final LogRepository logRepository;


    @Scheduled(cron = "0 0 * * * *")
//    @Scheduled(fixedDelay = 30000)
    public void deletarLogsScheduler() {
        List<LogEntity> logs = logRepository.findBydataLogLessThan(LocalDateTime.now().minusYears(1));
        log.info("Total de logs com mais de 1 ano {}", logs.stream().count());
        logRepository.deleteAll(logs);
    }
}
