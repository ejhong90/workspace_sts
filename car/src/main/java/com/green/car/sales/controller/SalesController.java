package com.green.car.sales.controller;

import com.green.car.manage.vo.CarInfoVO;
import com.green.car.sales.service.SalesServiceImpl;
import com.green.car.sales.vo.SalesInfoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Resource(name = "salesService")
    private SalesServiceImpl salesService;

    // 판매 정보 등록 페이지 이동
    @GetMapping("/addSaleInfo")
    public String addSaleInfo(Model model){
        List<CarInfoVO> sale = salesService.models();
        System.out.println(sale);
        model.addAttribute("categories", salesService.models());
        return "content/sales/sales_info";
    }

    // 판매 정보 등록 후 목록 페이지 이동
    @PostMapping("/saleList")
    public String saleList(SalesInfoVO salesInfoVO){
        salesService.insertSalesInfo(salesInfoVO);
        return "redirect:/sales/carList";
    }

    // 차량 목록
    @GetMapping("/carList")
    public String carList(Model model){
        model.addAttribute("list", salesService.selectSellList());
        return "content/sales/sale_list";
    }


}
