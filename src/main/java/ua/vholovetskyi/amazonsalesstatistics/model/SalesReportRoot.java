package ua.vholovetskyi.amazonsalesstatistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportRoot {

    @Id
    private String id = "1";

    @DBRef
    private ReportSpecification reportSpecification;

    @DBRef
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;

    @DBRef
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
