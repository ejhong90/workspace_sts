package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    // 장바구니 등록
    void insertPocket(CartVO cartVO);
    
    // 장바구니 목록 리스트
    List<CartViewVO> selectCartList(String memberId);

    // 장바구니 삭제 버튼 클릭
    void deleteCart(int cartCode);

    // 수량 수정 후 변경
    void changeCnt(CartVO cartVO);

    // 선택 삭제
    void deleteSelection(CartVO cartVO);

    // 선택한 상품 구매를 위한 장바구니 목록 조회
    List<CartViewVO> findCartListForBuy(CartVO cartVO);
}
