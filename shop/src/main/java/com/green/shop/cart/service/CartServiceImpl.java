package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    SqlSessionTemplate sqlSession;


    // 장바구니 등록 관련
    @Override
    public void insertPocket(CartVO cartVO) {
//        sqlSession.insert("cartMapper.insertPocket", cartVO);
        // 장바구니에 상품이 등록 여부 확인
        int num = sqlSession.selectOne("cartMapper.selectCartCnt", cartVO);

        // 장바구니에 상품 있을 경우
        if(num > 0){
            sqlSession.update("cartMapper.plusCartCnt", cartVO);
        }
        // 장바구니에 상품이 없을 경우
        else {
            sqlSession.insert("cartMapper.insertPocket", cartVO);
        }
    }

    // 장바구니 목록 조회
    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memberId);
    }
    
    // 삭제 버튼
    @Override
    public void deleteCart(int cartCode) {
        sqlSession.delete("cartMapper.deleteCart", cartCode);
    }

    // 수량 수정 + 변경 버튼
    @Override
    public void changeCnt(CartVO cartVO) {
        sqlSession.update("cartMapper.changeCnt", cartVO);
    }

    // 선택 삭제
    @Override
    public void deleteSelection(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteSelection", cartVO);

    }

    // 선택 상품 구매위한 장바구니 목록 조회
    @Override
    public List<CartViewVO> findCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.findCartListForBuy", cartVO);
    }



}
