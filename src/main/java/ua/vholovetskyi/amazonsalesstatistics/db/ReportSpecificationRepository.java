package ua.vholovetskyi.amazonsalesstatistics.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vholovetskyi.amazonsalesstatistics.model.ReportSpecification;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;

import java.util.Optional;

public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, String> {

}
