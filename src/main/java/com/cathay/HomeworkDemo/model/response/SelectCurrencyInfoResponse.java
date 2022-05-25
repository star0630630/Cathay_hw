package com.cathay.HomeworkDemo.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class SelectCurrencyInfoResponse {
    private String currency;
    private Float rate;
    private String currencyChinese;
    private Date createTime;
    private Date updateTime;
}
