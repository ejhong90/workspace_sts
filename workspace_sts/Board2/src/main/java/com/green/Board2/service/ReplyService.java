package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    
    // 댓글 작성
    void insertReply(ReplyVO replyVO);

    // 댓글 조회
    List<ReplyVO> selectReplyList(int boardNum);

    // 댓글 수정
    void updateReply();

    // 댓글 삭제
    void deleteReply();
}
