package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartService;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource(name = "cartService")
    private CartService cartService;

    // 장바구니 등록
    @ResponseBody
    @PostMapping("/insertCart")
    public void insertCart(CartVO cartVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        cartService.insertPocket(cartVO);

    }

    // 장바구니에 담아둔 상품 목록 조회
    @GetMapping("/list")
    public String showCartList(Model model, HttpSession session, @RequestParam(name = "page", required = false, defaultValue = "cartList") String page){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());
        model.addAttribute("cartList", cartList);
//        System.out.println(cartList);

        // 총 가격을 계산한 후 html로 전달
        int sum = 0;
        for(CartViewVO e : cartList){
            sum += e.getTotalPrice();
        }
        // side 화면 active 시키기위한 데이터 보내기
        model.addAttribute("page", page);
        //model.addAttribute("finalPrice", sum);
        return "content/cart/cart_list";
    }

    // 삭제 버튼
    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam(name = "cartCode") int cartCode){
        cartService.deleteCart(cartCode);
        return "redirect:/cart/list";
    }

    // 수량 수정
    @ResponseBody
    @PostMapping("/changeCnt")
    public void changeCnt(CartVO cartVO){
        cartService.changeCnt(cartVO);
        System.out.println(cartVO);
    }

    // 선택 삭제
    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO){
        cartService.deleteSelection(cartVO);
        return "redirect:/cart/list";
    }
}
