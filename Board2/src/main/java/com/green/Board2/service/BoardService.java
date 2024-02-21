package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {

    // 게시글 목록 조회
    List<BoardVO> selectBoardList(SearchVO searchVO);

    // 게시글 작성
    void insertBoard(BoardVO boardVO);

    // 게시글 상제 조회 화면
    BoardVO selectDetail(int boardNum);

    // 게시글 수정
    void updateBoard(BoardVO boardVO);

    // 게시글 삭제
    void deleteBoard(int boardNum);

    // 조회수 카운팅
    void updateReadCnt(int boardNum);

    // 게시판 전체 데이터 수
    int selectBoardCnt(SearchVO searchVO);


}
