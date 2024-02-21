package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 게시글 목록 조회
    @Override
    public List<BoardVO> selectBoard() {
        return sqlSession.selectList("boardMapper.selectBoard");
    }

    // 게시글 작성
    @Override
    public void writeForm(BoardVO boardVO) {
//        "mapper namespace.쿼리 id" , 빈값을 채울 것
        sqlSession.insert("boardMapper.insertBoard", boardVO);
    }

    // 게시글 상세 화면 조회
    @Override
    public BoardVO selectDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectDetail", boardNum);
    }

    // 선택한 게시글 삭제
    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    // 선택한 게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }

    // 조회수 카운팅
    @Override
    public void readCnt(int boardNum) {
        sqlSession.update("boardMapper.readCnt", boardNum);
    }


}
