package com.green.car.manage.service;

import com.green.car.manage.vo.CarInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{

    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public List<CarInfoVO> carList() {
        return sqlSession.selectList("carMapper.searchCars");
    }

    @Override
    public void insertCarInfo(CarInfoVO carInfoVO) {
        sqlSession.insert("carMapper.insertCarInfo", carInfoVO);
    }
}
