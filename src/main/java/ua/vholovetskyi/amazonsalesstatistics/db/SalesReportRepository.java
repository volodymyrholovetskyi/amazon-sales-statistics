package ua.vholovetskyi.amazonsalesstatistics.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesReportRoot;

import java.util.List;

public interface SalesReportRepository extends MongoRepository<SalesReportRoot, String> {

    @Query("{'salesAndTrafficByDate.date': ?0}")
    SalesAndTrafficByDate findSalesAndTrafficByDate(String date);

    @Query("{'id': ?0}")
    SalesReportRoot findBySalesId(String id);

    @Query("{'salesAndTrafficByDate.date' : { '$gte': ?0, '$lte': ?1 } }")
    public List<SalesAndTrafficByDate> getObjectByDate(String from, String to);
}
