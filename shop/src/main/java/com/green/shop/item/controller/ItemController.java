package com.green.shop.item.controller;

import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 상품과 관련된 모든 페이지 이동을 처리
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    // 상품 목록 페이지로 이동
    @RequestMapping("/list")
    public String list(Model model){
        List<ItemVO> imgList = itemService.showItemList();

        // 등록된 모든 상품 목록 조회
        model.addAttribute("itemList", imgList);

        return "content/item/item_list";
    }

    // 상세 정보 페이지
    @GetMapping("/itemDetail")
    public String itemDetail(@RequestParam(name = "itemCode") int itemCode, Model model){
        ItemVO itemVO = itemService.selectDetail(itemCode);
        model.addAttribute("detail", itemVO);
        System.out.println(itemVO);


        return "content/item/item_detail";
    }

}
