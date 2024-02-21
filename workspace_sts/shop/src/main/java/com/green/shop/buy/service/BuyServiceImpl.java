package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.ShopBuyVO;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService {

    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public int setNextBuyCode() {
        return sqlSession.selectOne("buyMapper.setNextBuyCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuys(ShopBuyVO shopBuyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuyInfo", shopBuyVO);
        sqlSession.insert("buyMapper.insertBuyDetails",  shopBuyVO);
        sqlSession.delete("cartMapper.deleteSelection", cartVO);
        for(BuyDetailVO e : shopBuyVO.getBuyDetailList()){
            sqlSession.update("buyMapper.minusStock", shopBuyVO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void directBuy(ShopBuyVO shopBuyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuyInfo", shopBuyVO);
        sqlSession.insert("buyMapper.insertDirectDetail", buyDetailVO);
        // 재고 차감
        sqlSession.update("buyMapper.minusStock", buyDetailVO);
    }

    @Override
    public List<ShopBuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }


}
