package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {

    // 조회한 카테고리 데이터를 상품 등록 화면 카테고리에 노출
    List<CategoryVO> showCategory();

    // 등록된 전체 상품 목록 조회
    List<ItemVO> showItemList();

    // 상세 정보 조회
    ItemVO selectDetail(int itemCode);





}
