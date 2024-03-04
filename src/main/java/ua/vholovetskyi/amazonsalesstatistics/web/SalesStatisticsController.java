package ua.vholovetskyi.amazonsalesstatistics.web;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.vholovetskyi.amazonsalesstatistics.application.SalesStatisticsService;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllAsins;
import ua.vholovetskyi.amazonsalesstatistics.application.statistics.ReportCountSummaryAllDates;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByAsin;
import ua.vholovetskyi.amazonsalesstatistics.model.SalesAndTrafficByDate;

import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
@Validated
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class SalesStatisticsController {

    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    private static final String ASIN_PATTERN = "^[0-9A-Z]{10}$";
    private static final String INCORRECT_DATE_FORMAT_MESSES = "Invalid format. Enter the format: yyyy-MM-dd";
    private static final String INCORRECT_ASIN_FORMAT_MESSES = "The ASIN code must contain only 10 symbols.";
    private SalesStatisticsService service;


    @GetMapping("/byDate")
    @Cacheable("specificDate")
    public SalesAndTrafficByDate getStatisticsForSpecificDate(
            @RequestParam(value = "date", defaultValue = "2024-02-14") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String date) {
        return service.getStatisticsForSpecificDate(date);
    }

    @GetMapping("/byPeriod")
    @Cacheable("periodDates")
    public List<SalesAndTrafficByDate> getStatisticsForPeriodOfDates(
            @RequestParam(value = "from", defaultValue = "2024-02-14") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String from,
            @RequestParam(value = "to", defaultValue = "2024-02-26") @Pattern(regexp = DATE_PATTERN, message = INCORRECT_DATE_FORMAT_MESSES) String to) {
        return service.getStatisticsForPeriodOfDates(from, to);
    }

    @GetMapping("/byAsin")
    @Cacheable("asin")
    public SalesAndTrafficByAsin getStatisticsForSpecificAsin(
            @RequestParam(value = "asin") @Pattern(regexp = ASIN_PATTERN, message = INCORRECT_ASIN_FORMAT_MESSES) String asin) {
        return service.getStatisticsForSpecificAsin(asin);
    }

    @GetMapping("/byAsins")
    @Cacheable("asins")
    public List<SalesAndTrafficByAsin> getStatisticsForAsinList(
            @RequestParam(value = "asins") List<@Pattern(regexp = ASIN_PATTERN, message = INCORRECT_ASIN_FORMAT_MESSES) String> asins) {
        return service.getStatisticsForAsinList(asins);
    }


    @GetMapping("/allPeriod")
    @Cacheable("allPeriod")
    public ReportCountSummaryAllDates getStatisticsForEntirePeriod() {
        return service.getSummaryStatisticsForEntirePeriod();
    }

    @GetMapping("/allAsins")
    @Cacheable("allAsins")
    public ReportCountSummaryAllAsins getStatisticsForAllAsin() {
        return service.getSummaryStatisticsForAllAsins();
    }

    @Caching(evict = {
            @CacheEvict(value = "specificDate", allEntries = true),
            @CacheEvict(value = "periodDates", allEntries = true),
            @CacheEvict(value = "asin", allEntries = true),
            @CacheEvict(value = "asins", allEntries = true),
            @CacheEvict(value = "allPeriod", allEntries = true),
            @CacheEvict(value = "allAsins", allEntries = true)})
    @GetMapping("/clearCache")
    @ResponseStatus(HttpStatus.OK)
    public void clearUsersCache() {
    }
}
