package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{

    @Autowired
    SqlSessionTemplate sqlSession;

    // DB에 저장된 카테고리 목록 조회
    @Override
    public List<CategoryVO> showCategory() {
        return sqlSession.selectList("itemMapper.category");
    }
    
    // 등록된 전체 상품 목록 조회
    @Override
    public List<ItemVO> showItemList() {
        return sqlSession.selectList("itemMapper.selectItemList");
    }

    // 상세 목록 조회
    @Override
    public ItemVO selectDetail(int itemCode) {
        return sqlSession.selectOne("itemMapper.selectDetail", itemCode);
    }




}
