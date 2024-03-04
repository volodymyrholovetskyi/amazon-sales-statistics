package ua.vholovetskyi.amazonsalesstatistics;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesReportRepository;

@Component
@RequiredArgsConstructor
public class Starter implements CommandLineRunner {

    private final SalesReportRepository repository;
    @Override
    public void run(String... args) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ClassPathResource classPathResource = new ClassPathResource("test_report.json");
//        SalesStatistics salesStatistics = objectMapper.readValue(classPathResource.getFile(), SalesStatistics.class);
//        System.out.println(salesStatistics);
//        repository.save(salesStatistics);
    }

}
