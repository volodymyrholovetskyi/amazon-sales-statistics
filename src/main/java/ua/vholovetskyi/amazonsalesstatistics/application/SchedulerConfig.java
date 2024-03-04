package ua.vholovetskyi.amazonsalesstatistics.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.scheduler.config")
public class SchedulerConfig {

    private final String fileName;
    private final String cron;
}
