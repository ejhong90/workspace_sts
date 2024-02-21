package com.green.car.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String mainIndex(){
        return "redirect:/manage/list";
    }
}
