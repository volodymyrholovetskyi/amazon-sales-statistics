package ua.vholovetskyi.amazonsalesstatistics.web;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.vholovetskyi.amazonsalesstatistics.application.SalesStatisticsService;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllAsins;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllDates;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByAsin;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesReportRoot;

import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
@Validated
public class SalesStatisticsController {

    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    private static final String ASIN_PATTERN = "^[0-9A-Z]{10}$";
    private static final String INCORRECT_DATE_FORMAT_MESSES = "Invalid format. Enter the format: yyyy-MM-dd";
    private static final String INCORRECT_ASIN_FORMAT_MESSES = "The ASIN code must contain only 10 symbols.";
    private SalesStatisticsService service;


    @GetMapping
    List<SalesReportRoot> getAll() {
        return service.getSalesReports();
    }

    @GetMapping("/{id}")
    SalesReportRoot getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/byDate")
    SalesAndTrafficByDate getStatisticsForSpecificDate(
            @RequestParam(value = "date", defaultValue = "2024-02-14") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String date) {
        return service.getStatisticsForSpecificDate(date);
    }

    @GetMapping("/byPeriod")
    List<SalesAndTrafficByDate> getStatisticsForPeriodOfDates(
            @RequestParam(value = "from", defaultValue = "2024-02-14") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String from,
            @RequestParam(value = "to", defaultValue = "2024-02-26") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String to) {
        return service.getStatisticsForPeriodOfDates(from, to);
    }

    @GetMapping("/byAsin")
    SalesAndTrafficByAsin getStatisticsForSpecificAsin(
            @RequestParam(value = "asin") @Pattern(regexp = ASIN_PATTERN, message = INCORRECT_ASIN_FORMAT_MESSES) String asin) {
        return service.getStatisticsForSpecificAsin(asin);
    }

    @GetMapping("/byAsins")
    List<SalesAndTrafficByAsin> getStatisticsForAsinList(
            @RequestParam(value = "asins") List<@Pattern(regexp = ASIN_PATTERN, message = INCORRECT_ASIN_FORMAT_MESSES)String> asins) {
        return service.getStatisticsForAsinList(asins);
    }


    @GetMapping("/allPeriod")
    ReportCountSummaryAllDates getStatisticsForEntirePeriod() {
        return service.getSummaryStatisticsForEntirePeriod();
    }

    @GetMapping("/allAsins")
    ReportCountSummaryAllAsins getStatisticsForAllAsin() {
        return service.getSummaryStatisticsForAllAsins();
    }
}
