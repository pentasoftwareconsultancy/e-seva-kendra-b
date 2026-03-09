package com.example.E_seva_kendra.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_seva_kendra.repository.PaymentRepository;

@Service
public class ReportService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Map<String,Object>> getWeeklyReport(){

        List<Object[]> data = paymentRepository.getWeeklyEarnings();
        List<Map<String,Object>> result = new ArrayList<>();

        for(Object[] row : data){
            Map<String,Object> map = new HashMap<>();
            map.put("name", row[0]);
            map.put("value", row[1]);
            result.add(map);
        }

        return result;
    }

    public List<Map<String,Object>> getMonthlyReport(){

        List<Object[]> data = paymentRepository.getMonthlyEarnings();
        List<Map<String,Object>> result = new ArrayList<>();

        for(Object[] row : data){
            Map<String,Object> map = new HashMap<>();
            map.put("name", row[0]);
            map.put("value", row[1]);
            result.add(map);
        }

        return result;
    }

    public List<Map<String,Object>> getYearlyReport(){

        List<Object[]> data = paymentRepository.getYearlyEarnings();
        List<Map<String,Object>> result = new ArrayList<>();

        for(Object[] row : data){
            Map<String,Object> map = new HashMap<>();
            map.put("name", row[0]);
            map.put("value", row[1]);
            result.add(map);
        }

        return result;
    }

}