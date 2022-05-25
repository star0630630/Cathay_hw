package com.cathay.HomeworkDemo.controller;

import com.cathay.HomeworkDemo.model.request.CreateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.request.UpdateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.response.QueryByCurrencyResponse;
import com.cathay.HomeworkDemo.model.response.QueryCoinDeskResponse;
import com.cathay.HomeworkDemo.model.response.SelectCurrencyInfoResponse;
import com.cathay.HomeworkDemo.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    @GetMapping("/queryCoinDesk")
    public QueryCoinDeskResponse queryCoinDesk(){
        return homeworkService.queryCoinDesk();
    }

    @GetMapping("/queryByCurrency/{currency}")
    public QueryByCurrencyResponse queryByCurrency(@PathVariable String currency){
        return homeworkService.queryByCurrency(currency);
    }

    /*
    *參考數據
    * currency：GBP
    * {
        "rate":32240.4137,
        "currencyChinese":"英鎊",
        "createTime":"2022-05-24",
        "updateTime":"2022-05-24"
       }
     */
    @PostMapping("/createCurrencyInfo/{currency}")
    public ResponseEntity<String> createCurrencyInfo(@PathVariable String currency,
                                                     @RequestBody CreateCurrencyInfoRequest createCurrencyInfoRequest){
        homeworkService.createCurrencyInfo(currency,createCurrencyInfoRequest);
        return ResponseEntity.ok("新增完成");
    }

    @GetMapping("/selectCurrencyInfo")
    public List<SelectCurrencyInfoResponse> selectCurrencyInfo(){
        return homeworkService.selectCurrencyInfo();
    }

    @PatchMapping("/updateCurrencyInfo/{currency}")
    public ResponseEntity<String> updateCurrencyInfo(@PathVariable String currency,
                                                     @RequestBody UpdateCurrencyInfoRequest updateCurrencyInfoRequest){
        homeworkService.updateCurrencyInfo(currency,updateCurrencyInfoRequest);
        return ResponseEntity.ok("更新完成");
    }

    @DeleteMapping("/deleteCurrencyInfo/{currency}")
    public ResponseEntity<String> deleteCurrencyInfo(@PathVariable String currency){
        homeworkService.deleteCurrencyInfo(currency);
        return ResponseEntity.ok("刪除完成");
    }


}
