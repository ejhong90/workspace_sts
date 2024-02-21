package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // transactional : 메소드 내부의 쿼리 실행은 모든 쿼리가 실행 성공 시 COMMIT
    //  하나라도 실행 중 실패 시, ROLLBACK
    // 상품 데이터 및 이미지 등록 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.addStuff", itemVO); // 아이템 등록
        sqlSession.insert("adminMapper.insertImgs", itemVO); // 이미지 등록
    }


    // 아이템 코드 넘버링
    @Override
    public int selectNextItemCode() {
        return  sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    // 구매 내역 조회
    @Override
    public List<ShopBuyVO> selectAdminBuys(SearchBuyVO searchBuyVO) {
        return sqlSession.selectList("adminMapper.selectBuyList", searchBuyVO);
    }

    // 선택한 상품의 상세 정보 조회
    @Override
    public ShopBuyVO selectDetailHistory(int buyCode) {
        return sqlSession.selectOne("adminMapper.detailBuyList", buyCode);
    }

    // 상품 정보 등록 목록 조회
    @Override
    public List<ItemVO> selectItemCondition() {
        return sqlSession.selectList("adminMapper.selectItems");
    }

    @Override
    public ItemVO showDetail(int itemCode) {
        return sqlSession.selectOne("adminMapper.showDetailItem", itemCode);
    }

    @Override
    public int updateItem(ItemVO itemVO) {
        return sqlSession.update("adminMapper.updateItem", itemVO);
    }


}
