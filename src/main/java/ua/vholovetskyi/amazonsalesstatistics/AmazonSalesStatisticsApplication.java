package ua.vholovetskyi.amazonsalesstatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.vholovetskyi.amazonsalesstatistics.application.SchedulerConfig;

@SpringBootApplication
@EnableConfigurationProperties(SchedulerConfig.class)
@EnableScheduling
public class AmazonSalesStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonSalesStatisticsApplication.class, args);
    }

}
