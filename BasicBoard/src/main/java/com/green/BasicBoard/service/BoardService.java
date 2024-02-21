package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.BoardVO;

import java.util.List;

public interface BoardService {

    // 게시판 조회
    List<BoardVO> selectBoard();

    // 게시판 글 작성
    void writeForm(BoardVO boardVO);

    // 글 상세 조회 화면
    BoardVO selectDetail(int boardNum);

    // 글 삭제하기
    void deleteBoard(int boardNum);

    // 글 수정하기
    void updateBoard(BoardVO boardVO);

    // 조회수 카운팅
    void readCnt(int boardNum);



}