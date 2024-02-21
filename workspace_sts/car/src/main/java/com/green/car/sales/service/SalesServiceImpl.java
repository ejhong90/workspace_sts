package com.green.car.sales.service;

import com.green.car.manage.vo.CarInfoVO;
import com.green.car.sales.vo.SalesInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("salesService")
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<CarInfoVO> models() {
        return sqlSession.selectList("salesMapper.modelNameToCate");
    }

    @Override
    public void insertSalesInfo(SalesInfoVO salesInfoVO) {
        sqlSession.insert("salesMapper.insertSalesInfo", salesInfoVO);
    }

    @Override
    public List<SalesInfoVO> selectSellList() {
        return sqlSession.selectList("salesMapper.selectSellList");
    }
}
