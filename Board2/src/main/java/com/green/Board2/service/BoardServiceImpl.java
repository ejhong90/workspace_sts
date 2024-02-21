package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 게시글 목록 조회
    @Override
    public List<BoardVO> selectBoardList(SearchVO searchVO) {
        return sqlSession.selectList("boardMapper.selectBoardList", searchVO);
    }

    // 게시글 작성
    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("boardMapper.insertBoard", boardVO);
    }

    // 게시글 상세 조회
    @Override
    public BoardVO selectDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectDetail", boardNum);
    }

    // 게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }

    // 게시글 삭제
    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    // 조회수 카운팅
    @Override
    public void updateReadCnt(int boardNum) {
        sqlSession.update("boardMapper.updateReadCnt", boardNum);
    }

    // 게시판 전체 데이터 수
    @Override
    public int selectBoardCnt(SearchVO searchVO) {
        return sqlSession.selectOne("boardMapper.selectBoardCnt",searchVO);
    }


}
