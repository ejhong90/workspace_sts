package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 회원가입 시 회원정보 DB 인서트
    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.insertMember", memberVO);
    }

    @Override
    public MemberVO selectLogin(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }
}
