package com.green.shop.admin.service;


import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    // 상품 + 이미지 데이터 등록
    void insertItem(ItemVO itemVO);

    // 다음에 들어갈 아이템 코드 조회
    int selectNextItemCode();

    // 구매 내역 조회 (관리자)
    List<ShopBuyVO> selectAdminBuys(SearchBuyVO searchBuyVO);

    // 구매 상세 내역
    ShopBuyVO selectDetailHistory(int buyCode);

    // 상품 정보 목록 조회
    List<ItemVO> selectItemCondition();

    // 상품 정보 상세 조회
    ItemVO showDetail(int itemCode);

    // 아이템 정보 변경
    int updateItem(ItemVO itemVO);
}
