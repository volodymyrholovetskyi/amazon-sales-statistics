package ua.vholovetskyi.amazonsalesstatistics.application.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportCountSummaryAllAsins {

    private double totalOrderItems;
    private double totalOrderItemsB2B;
    private double totalOrderedProductSales;
    private double totalOrderedProductSalesB2B;
    private double totalPageViews;
    private double totalSessions;
    private double totalSessionsB2B;

}
