package com.tim.app;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class DashboardController {
    public DashboardController(){

    }

    @RequestMapping("/")
    public String index(){
        return "hlelo";
    }
}
