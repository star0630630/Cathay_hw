package com.cathay.HomeworkDemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "coinService", url = "https://api.coindesk.com/v1/bpi")
public interface CoinDeskFeign {

    @GetMapping(path = "/currentprice.json" , consumes = "application/javascript")
    String coin();
}
