package com.cathay.HomeworkDemo.service;

import com.cathay.HomeworkDemo.model.request.CreateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.request.UpdateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.response.QueryByCurrencyResponse;
import com.cathay.HomeworkDemo.model.response.QueryCoinDeskResponse;
import com.cathay.HomeworkDemo.model.response.SelectCurrencyInfoResponse;

import java.util.List;


public interface HomeworkService {
    QueryCoinDeskResponse queryCoinDesk();
    QueryByCurrencyResponse queryByCurrency(String currency);
    void createCurrencyInfo(String currency, CreateCurrencyInfoRequest createCurrencyInfoRequest);
    List<SelectCurrencyInfoResponse> selectCurrencyInfo();
    void updateCurrencyInfo(String currency, UpdateCurrencyInfoRequest updateCurrencyInfoRequest);
    void deleteCurrencyInfo(String currency);
}
