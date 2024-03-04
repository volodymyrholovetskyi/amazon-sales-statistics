package ua.vholovetskyi.amazonsalesstatistics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("sales_and_traffic_by_date")
public class SalesAndTrafficByDate {
    @Id
    private String id;
    private String date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;

}
