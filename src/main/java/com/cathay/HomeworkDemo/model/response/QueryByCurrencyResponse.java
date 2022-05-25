package com.cathay.HomeworkDemo.model.response;

import com.cathay.HomeworkDemo.model.feign.request.Bpi;
import com.cathay.HomeworkDemo.model.feign.request.Time;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryByCurrencyResponse {
    private String code;
    private String symbol;
    private String rate;
    private String description;

    @JsonProperty("rate_float")
    private Float rateFloat;
}
