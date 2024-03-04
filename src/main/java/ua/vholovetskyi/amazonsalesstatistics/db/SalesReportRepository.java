package ua.vholovetskyi.amazonsalesstatistics.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesReportRoot;

public interface SalesReportRepository extends MongoRepository<SalesReportRoot, String> {

}
