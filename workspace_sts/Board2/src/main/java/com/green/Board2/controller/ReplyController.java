package com.green.Board2.controller;

import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    // 댓글 등록
    @PostMapping("/regReply")
    public String insertReply(ReplyVO replyVO, HttpSession session){
        // 로그인한 유저 정보 가져오기
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        // 회원 ID 데이터를 작성자에 넣기
        replyVO.setWriter(loginInfo.getMemberId());
        // 작성한 데이터 입력
        replyService.insertReply(replyVO);
        // 댓글 입력 버튼을 누르면 가고자 하는 controller로 데이터 전달
        return "redirect:/board/content?boardNum=" + replyVO.getBoardNum();
    }
    



}
