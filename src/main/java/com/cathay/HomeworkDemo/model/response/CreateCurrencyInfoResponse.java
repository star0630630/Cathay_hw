package com.cathay.HomeworkDemo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CreateCurrencyInfoResponse {
    private String currency;
    private Float rate;
    private String currencyChinese;
    private Date createTime;
    private Date updateTime;
}
