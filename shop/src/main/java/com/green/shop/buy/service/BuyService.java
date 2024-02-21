package com.green.shop.buy.service;


import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface BuyService {

    int setNextBuyCode();

    // 장바구니 상품 구매
    void insertBuys(ShopBuyVO shopBuyVO, CartVO cartVO);

    // 바로 구매
    void directBuy(ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO);

    // 사용자용 구매 목록 조회
    List<ShopBuyVO> selectBuyList(String memberId);


}
