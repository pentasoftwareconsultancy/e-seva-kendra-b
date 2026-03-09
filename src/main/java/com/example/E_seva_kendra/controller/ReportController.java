package com.example.E_seva_kendra.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/week")
    public List<Map<String,Object>> getWeeklyReport(){
        return reportService.getWeeklyReport();
    }

    @GetMapping("/month")
    public List<Map<String,Object>> getMonthlyReport(){
        return reportService.getMonthlyReport();
    }

    @GetMapping("/year")
    public List<Map<String,Object>> getYearlyReport(){
        return reportService.getYearlyReport();
    }

}