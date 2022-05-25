package com.cathay.HomeworkDemo.model.response;

import com.cathay.HomeworkDemo.model.feign.request.Bpi;
import com.cathay.HomeworkDemo.model.feign.request.Time;
import lombok.Data;

@Data
public class QueryCoinDeskResponse {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Bpi bpi;
}
