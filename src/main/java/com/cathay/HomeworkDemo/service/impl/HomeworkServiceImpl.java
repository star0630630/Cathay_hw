package com.cathay.HomeworkDemo.service.impl;

import com.cathay.HomeworkDemo.feign.CoinDeskFeign;
import com.cathay.HomeworkDemo.model.entity.CurrencyTable;
import com.cathay.HomeworkDemo.model.repository.CoinDeskRepository;
import com.cathay.HomeworkDemo.model.request.CreateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.request.UpdateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.response.QueryByCurrencyResponse;
import com.cathay.HomeworkDemo.model.response.QueryCoinDeskResponse;
import com.cathay.HomeworkDemo.model.response.SelectCurrencyInfoResponse;
import com.cathay.HomeworkDemo.service.HomeworkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("HomeworkService")
@RequiredArgsConstructor
@Slf4j
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    CoinDeskFeign coinDeskFeign;

    @Autowired
    CoinDeskRepository coinDeskRepository;

    @Override
    public QueryCoinDeskResponse queryCoinDesk(){
        String coinRes = coinDeskFeign.coin();
        ObjectMapper objectMapper = new ObjectMapper();
        QueryCoinDeskResponse queryCoinDeskResponse = new QueryCoinDeskResponse();
        try {
            Map<String, Object> coinMap = objectMapper.readValue(coinRes, Map.class);
            queryCoinDeskResponse = objectMapper.convertValue(coinMap, QueryCoinDeskResponse.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException("查詢失敗");
        }
        return queryCoinDeskResponse;
    }

    @Override
    public QueryByCurrencyResponse queryByCurrency(String currency){
        QueryCoinDeskResponse queryCoinDeskResponse = new QueryCoinDeskResponse();
        queryCoinDeskResponse = this.queryCoinDesk();
        ObjectMapper objectMapper = new ObjectMapper();

        QueryByCurrencyResponse queryByCurrencyResponse = new QueryByCurrencyResponse();
        if("USD".equals(currency) || "usd".equals(currency)){
            queryByCurrencyResponse.setCode(queryCoinDeskResponse.getBpi().getUsd().getCode());
            queryByCurrencyResponse.setSymbol(queryCoinDeskResponse.getBpi().getUsd().getSymbol());
            queryByCurrencyResponse.setRate(queryCoinDeskResponse.getBpi().getUsd().getRate());
            queryByCurrencyResponse.setDescription(queryCoinDeskResponse.getBpi().getUsd().getDescription());
            queryByCurrencyResponse.setRateFloat(queryCoinDeskResponse.getBpi().getUsd().getRateFloat());
        }else if("EUR".equals(currency) || "eur".equals(currency)){
            queryByCurrencyResponse.setCode(queryCoinDeskResponse.getBpi().getEur().getCode());
            queryByCurrencyResponse.setSymbol(queryCoinDeskResponse.getBpi().getEur().getSymbol());
            queryByCurrencyResponse.setRate(queryCoinDeskResponse.getBpi().getEur().getRate());
            queryByCurrencyResponse.setDescription(queryCoinDeskResponse.getBpi().getEur().getDescription());
            queryByCurrencyResponse.setRateFloat(queryCoinDeskResponse.getBpi().getEur().getRateFloat());
        }else if("GBP".equals(currency) || "gbp".equals(currency)){
            queryByCurrencyResponse.setCode(queryCoinDeskResponse.getBpi().getGbp().getCode());
            queryByCurrencyResponse.setSymbol(queryCoinDeskResponse.getBpi().getGbp().getSymbol());
            queryByCurrencyResponse.setRate(queryCoinDeskResponse.getBpi().getGbp().getRate());
            queryByCurrencyResponse.setDescription(queryCoinDeskResponse.getBpi().getGbp().getDescription());
            queryByCurrencyResponse.setRateFloat(queryCoinDeskResponse.getBpi().getGbp().getRateFloat());
        }else {
            throw new RuntimeException("無此幣別");
        }

        return queryByCurrencyResponse;
    }

    @Override
    public void createCurrencyInfo(String currency, CreateCurrencyInfoRequest createCurrencyInfoRequest){
        CurrencyTable currencyTable = new CurrencyTable();
        currencyTable.setCurrency(currency);
        currencyTable.setRate(createCurrencyInfoRequest.getRate());
        currencyTable.setCurrencyChinese(createCurrencyInfoRequest.getCurrencyChinese());
        currencyTable.setCreateTime(createCurrencyInfoRequest.getCreateTime());
        currencyTable.setUpdateTime(createCurrencyInfoRequest.getUpdateTime());

        if(coinDeskRepository.findByCurrency(currency).size() != 0){
            throw new RuntimeException("已有資料");
        }

        try{
            coinDeskRepository.save(currencyTable);
        } catch(Exception e){
            throw new RuntimeException("新增失敗");
        }

    }

    @Override
    public List<SelectCurrencyInfoResponse> selectCurrencyInfo() {
        List<SelectCurrencyInfoResponse> selectCurrencyInfoResponseList = new ArrayList<>();
        for(int i=0; i<coinDeskRepository.count();i++){
            SelectCurrencyInfoResponse selectCurrencyInfoResponse = new SelectCurrencyInfoResponse();
            selectCurrencyInfoResponse.setCurrency(Streamable.of(coinDeskRepository.findAll()).toList().get(i).getCurrency());
            selectCurrencyInfoResponse.setCurrencyChinese(Streamable.of(coinDeskRepository.findAll()).toList().get(i).getCurrencyChinese());
            selectCurrencyInfoResponse.setRate(Streamable.of(coinDeskRepository.findAll()).toList().get(i).getRate());
            selectCurrencyInfoResponse.setCreateTime(Streamable.of(coinDeskRepository.findAll()).toList().get(i).getCreateTime());
            selectCurrencyInfoResponse.setUpdateTime(Streamable.of(coinDeskRepository.findAll()).toList().get(i).getUpdateTime());
            selectCurrencyInfoResponseList.add(selectCurrencyInfoResponse);
        }
        return selectCurrencyInfoResponseList;
    }

    @Override
    public void updateCurrencyInfo(String currency, UpdateCurrencyInfoRequest updateCurrencyInfoRequest) {
        CurrencyTable currencyTable = new CurrencyTable();
        currencyTable.setCurrency(currency);
        currencyTable.setRate(updateCurrencyInfoRequest.getRate());
        currencyTable.setCurrencyChinese(updateCurrencyInfoRequest.getCurrencyChinese());
        currencyTable.setCreateTime(updateCurrencyInfoRequest.getCreateTime());
        currencyTable.setUpdateTime(updateCurrencyInfoRequest.getUpdateTime());

        if (coinDeskRepository.findByCurrency(currency).size() == 0) {
            throw new RuntimeException("查無資料");
        }

        try {
            coinDeskRepository.save(currencyTable);
        } catch (Exception e) {
            throw new RuntimeException("更新失敗");
        }

    }

    @Override
    public void deleteCurrencyInfo(String currency) {
        CurrencyTable currencyTable = new CurrencyTable();
        currencyTable = coinDeskRepository.findByCurrency(currency).get(0);

        if(currencyTable == null){
            throw new RuntimeException("查無資料");
        }

        try {
            coinDeskRepository.delete(currencyTable);
        }catch (Exception e){
            throw new RuntimeException("刪除失敗");
        }
    }

}
