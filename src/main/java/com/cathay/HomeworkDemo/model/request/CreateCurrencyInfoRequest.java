package com.cathay.HomeworkDemo.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCurrencyInfoRequest {
    private Float rate;
    private String currencyChinese;
    private Date createTime;
    private Date updateTime;
}
