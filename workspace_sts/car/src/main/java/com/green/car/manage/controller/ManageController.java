package com.green.car.manage.controller;

import com.green.car.manage.service.CarServiceImpl;
import com.green.car.manage.vo.CarInfoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Resource(name = "carService")
    private CarServiceImpl carService;

    // 메인 화면
    @GetMapping("/list")
    public String insertCar(){
        return "content/main/first_list";
    }

    // 차량 관리 버튼 클릭 시
    @GetMapping("/insertInform")
    public String insertInfo(Model model){
        List<CarInfoVO> carList = carService.carList();
        model.addAttribute("carList", carList);
        return "content/management/manage";
    }

    // 차량 등록 버튼 클릭 시
    @PostMapping("/addModel")
    public String addModel(CarInfoVO carInfoVO){
        carService.insertCarInfo(carInfoVO);
        return "redirect:/manage/insertInform";
    }
}
