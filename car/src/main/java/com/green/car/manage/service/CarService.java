package com.green.car.manage.service;


import com.green.car.manage.vo.CarInfoVO;

import java.util.List;

public interface CarService {


    // 등록한 차량 목록 조회
    List<CarInfoVO> carList();

    // 차량 정보 등록
    void insertCarInfo(CarInfoVO carInfoVO);
}
