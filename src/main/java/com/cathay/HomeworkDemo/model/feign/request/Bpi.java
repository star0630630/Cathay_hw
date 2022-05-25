package com.cathay.HomeworkDemo.model.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bpi {
    @JsonProperty("USD")
    private CoinInfo usd;
    @JsonProperty("GBP")
    private CoinInfo gbp;
    @JsonProperty("EUR")
    private CoinInfo eur;
}
