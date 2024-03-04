package ua.vholovetskyi.amazonsalesstatistics.db;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllAsins;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllDates;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByAsin;

import java.util.List;
import java.util.Optional;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    @Query("{'parentAsin': {'$eq': ?0}}")
    Optional<SalesAndTrafficByAsin> findSalesAndTrafficByAsin(String parentAsin);

    @Query("{'parentAsin': {'$in': ?0}}")
    List<SalesAndTrafficByAsin> getStatisticsForAsinList(List<String> parentAsin);


    @Aggregation(pipeline = {
            "{$group: {" +
                    "_id: null," +
                    "totalPageViews: {$sum: '$trafficByAsin.pageViews'}," +
                    "totalOrderItems: {$sum: '$salesByAsin.totalOrderItems'}," +
                    "totalOrderItemsB2B: {$sum: '$salesByAsin.totalOrderItemsB2B'}," +
                    "totalOrderedProductSales: {$sum: '$salesByAsin.orderedProductSales.amount'}," +
                    "totalOrderedProductSalesB2B: {$sum: '$salesByAsin.orderedProductSalesB2B.amount'}," +
                    "totalSessions: {$sum: '$trafficByAsin.sessions'}," +
                    "totalSessionsB2B: {$sum: '$trafficByAsin.sessionsB2B'}}}"
    })
    ReportCountSummaryAllAsins getSummaryStatisticsForAllAsin();
}
