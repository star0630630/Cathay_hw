package com.cathay.HomeworkDemo.model.repository;

import com.cathay.HomeworkDemo.model.entity.CurrencyTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinDeskRepository extends CrudRepository<CurrencyTable,Integer> {

    List<CurrencyTable> findByCurrency(String currency);
}
