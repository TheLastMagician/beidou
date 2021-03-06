package com.beidou.position.business.controller;


import com.beidou.position.business.entity.History;
import com.beidou.position.business.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    HistoryService historyService;

    @RequestMapping("/history")
    public List<History> getByCarId(@RequestParam("carId")Integer carId){
        List<History> histories=historyService.getByCarId(carId);
        System.out.println(histories);
        return histories;
    }

}
