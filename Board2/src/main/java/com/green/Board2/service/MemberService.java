package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;

public interface MemberService {

    // 회원가입 시 회원 정보 insert
    void insertMember(MemberVO memberVO);

    MemberVO selectLogin(MemberVO memberVO);
}
