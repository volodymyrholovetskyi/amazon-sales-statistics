package ua.vholovetskyi.amazonsalesstatistics.application;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.amazonsalesstatistics.db.ReportSpecificationRepository;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesAndTrafficByAsinRepository;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesAndTrafficByDateRepository;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesReportRepository;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesReportRoot;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.cronutils.model.CronType.QUARTZ;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseUpdateJob {

    private final SalesReportRepository repository;
    private final SalesAndTrafficByDateRepository dateRepository;
    private final SalesAndTrafficByAsinRepository asinRepository;
    private final ReportSpecificationRepository specificationRepository;
    private final SchedulerConfig config;
    private final ObjectMapper jsonMapper;

    @Scheduled(cron = "${app.scheduler.config.cron}")
    void run() {
        log.info("Run Scheduler: {}", LocalDateTime.now());
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(config.getFileName()))) {

            SalesReportRoot salesReportRoot = jsonMapper.readValue(bufferedReader, SalesReportRoot.class);

            dateRepository.deleteAll();
            dateRepository.saveAll(salesReportRoot.getSalesAndTrafficByDate());

            asinRepository.deleteAll();
            asinRepository.saveAll(salesReportRoot.getSalesAndTrafficByAsin());

            specificationRepository.deleteAll();
            specificationRepository.save(salesReportRoot.getReportSpecification());

            repository.save(salesReportRoot);
            log.info("Next Scheduler run: {}", nextRunDate());
        } catch (Exception e) {
            log.error("Failed to save to DB. Error message: {}", e.getMessage());
            throw new IllegalStateException("Failed to save to DB", e);
        }
    }


    private String nextRunDate() {
        return parseCron();
    }

    private String parseCron() {
        var cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        var parser = new CronParser(cronDefinition);
        parser.parse(config.getCron());
        var descriptor = CronDescriptor.instance();
        return descriptor.describe(parser.parse(config.getCron()));
    }
}
