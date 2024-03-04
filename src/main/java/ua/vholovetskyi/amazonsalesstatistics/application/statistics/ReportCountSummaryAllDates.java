package ua.vholovetskyi.amazonsalesstatistics.application.statistics;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportCountSummaryAllDates {

    private double totalOrderItems;
    private double totalOrderItemsB2B;
    private double totalOrderedProductSales;
    private double totalOrderedProductSalesB2B;
    private double totalPageViews;
    private double totalNegativeFeedbackReceived;

}
