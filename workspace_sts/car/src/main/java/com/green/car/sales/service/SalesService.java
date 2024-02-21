package com.green.car.sales.service;

import com.green.car.manage.vo.CarInfoVO;
import com.green.car.sales.vo.SalesInfoVO;

import java.util.List;

public interface SalesService {

    // 판매 정보 페이지 카테고리 조회
    List<CarInfoVO> models();

    // 판매 정보 등록
    void insertSalesInfo(SalesInfoVO salesInfoVO);

    // 판매 목록 전체 조회
    List<SalesInfoVO> selectSellList();

}
