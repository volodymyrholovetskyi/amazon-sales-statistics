package ua.vholovetskyi.amazonsalesstatistics.db;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllDates;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;

import java.util.List;
import java.util.Optional;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {

    @Query("{'date': {'$eq': ?0}}")
    Optional<SalesAndTrafficByDate> findSalesAndTrafficByDate(String date);

    @Query("{'date' : { '$gte': ?0, '$lte': ?1 } }")
    List<SalesAndTrafficByDate> findSalesForPeriodOfDates(String from, String to);

    @Aggregation(pipeline = {
            "{$group: {" +
                    "_id: null," +
                    "totalPageViews: {$sum: '$trafficByDate.pageViews'}," +
                    "totalOrderItems: {$sum: '$salesByDate.totalOrderItems'}," +
                    "totalOrderItemsB2B: {$sum: '$salesByDate.totalOrderItemsB2B'}," +
                    "totalOrderedProductSales: {$sum: '$salesByDate.orderedProductSales.amount'}," +
                    "totalOrderedProductSalesB2B: {$sum: '$salesByDate.orderedProductSalesB2B.amount'}," +
                    "totalNegativeFeedbackReceived: {$sum: 'trafficByDate.negativeFeedbackReceived'}}}"
    })
    ReportCountSummaryAllDates getSummaryStatisticsForAllDates();
}

