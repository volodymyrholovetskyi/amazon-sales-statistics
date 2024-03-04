package ua.vholovetskyi.amazonsalesstatistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductSalesB2B {
    private double amount;
    private String currencyCode;
}
