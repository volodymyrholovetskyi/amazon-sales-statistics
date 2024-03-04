package ua.vholovetskyi.amazonsalesstatistics.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllAsins;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllDates;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesAndTrafficByAsinRepository;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesAndTrafficByDateRepository;
import ua.vholovetskyi.amazonsalesstatistics.db.SalesReportRepository;
import ua.vholovetskyi.amazonsalesstatistics.exception.ResourceNotFound;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByAsin;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesReportRoot;

import java.util.List;

@Service
@AllArgsConstructor
public class SalesStatisticsService {

    private final SalesReportRepository repository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    public List<SalesReportRoot> getSalesReports() {
        return repository.findAll();
    }

    public SalesReportRoot getById(String id) {
        return repository.findBySalesId(id);
    }

        public SalesAndTrafficByDate getStatisticsForSpecificDate(String date) {
            return salesAndTrafficByDateRepository.findSalesAndTrafficByDate(date)
                    .orElseThrow(() -> new ResourceNotFound("DATE", date));
    }

    public List<SalesAndTrafficByDate> getStatisticsForPeriodOfDates(String from, String to) {
        return repository.getObjectByDate(from, to);
    }

    public SalesAndTrafficByAsin getStatisticsForSpecificAsin(String asin) {
        return salesAndTrafficByAsinRepository.findSalesAndTrafficByAsin(asin)
                .orElseThrow(() -> new ResourceNotFound("ASIN", asin));
    }

    public List<SalesAndTrafficByAsin> getStatisticsForAsinList(List<String> asins) {
        return salesAndTrafficByAsinRepository.getStatisticsForAsinList(asins);
    }

    public ReportCountSummaryAllDates getSummaryStatisticsForEntirePeriod() {
        return salesAndTrafficByDateRepository.getSummaryStatisticsForAllDates();
    }

    public ReportCountSummaryAllAsins getSummaryStatisticsForAllAsins() {
        return salesAndTrafficByAsinRepository.getSummaryStatisticsForAllAsin();
    }

}


