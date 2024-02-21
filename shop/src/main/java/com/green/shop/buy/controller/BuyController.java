package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Resource(name = "buyService")
    private BuyServiceImpl buyService;

    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    @GetMapping("/list")
    public String insertBuyList(CartVO cartVO, HttpSession session){
        // 구매 상세 테이블의 insert 할 itemCode , buyCnt, totalPrice 조회
        List<CartViewVO> list = cartService.findCartListForBuy(cartVO);

        // 구매 정보 테이블에 삽입할 BUY_PRICE 계산(구매 총 가격)
        int buyPrice = 0;
        for(CartViewVO e : list){
            buyPrice = buyPrice + e.getTotalPrice();
        }

        // 구매자 id 가져오기
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        // 다음에 들어갈 buy_code 결정하기
        int buyCode = buyService.setNextBuyCode();

        // 구매 정보 및 구매 상세 테이블에 insert
        ShopBuyVO shopBuyVO = new ShopBuyVO();
        shopBuyVO.setBuyCode(buyCode);
        shopBuyVO.setMemberId(memberId);
        shopBuyVO.setBuyPrice(buyPrice);

        // 구매 상세 테이블
        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for(CartViewVO cartView : list){
            BuyDetailVO vo = new BuyDetailVO();
            vo.setItemCode(cartView.getItemCode());
            vo.setBuyCnt(cartView.getCartCnt());
            vo.setTotalPrice(cartView.getTotalPrice());
            buyDetailList.add(vo);
        }
        shopBuyVO.setBuyDetailList(buyDetailList);

        buyService.insertBuys(shopBuyVO, cartVO);

        return "redirect:/";
    }

    @PostMapping("/directBuy")
    public String directBuy(ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO, HttpSession session){
        // 다음에 들어가야 하는 buy_code 값 조회
        int buyCode = buyService.setNextBuyCode();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        shopBuyVO.setMemberId(loginInfo.getMemberId());
        shopBuyVO.setBuyPrice(buyDetailVO.getTotalPrice());
        shopBuyVO.setBuyCode(buyCode);
        buyDetailVO.setBuyCode(buyCode);

        buyService.directBuy(shopBuyVO, buyDetailVO);

        return "redirect:/";
    }


    // 구매 이력 페이지 (사용자용)
    @GetMapping("history")
    public String goHistory(@RequestParam(name = "page", required = false, defaultValue = "history")String page, Model model
                            , HttpSession session){
        model.addAttribute("page", page);

        // 로그인 정보
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        // 구매 목록 조회
        List<ShopBuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());
        for(ShopBuyVO buy : buyList){
            System.out.println(buy);
        }
        model.addAttribute("buyList", buyList);
        return "content/buy/history";
    }
}
