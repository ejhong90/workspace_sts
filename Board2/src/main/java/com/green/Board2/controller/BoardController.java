package com.green.Board2.controller;

import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.PageVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

//     @RequestMapping : get 방식, post 방식 둘 다 실행 가능한 녀석
    // 게시글 목록 조회
    @RequestMapping("/goBoardList")
    public  String goBoardList(SearchVO searchVO, Model model){
        // 전체 게시물의 수 카운팅(전체 데이터 수)
        int totalDataCnt = boardService.selectBoardCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
        System.out.println(totalDataCnt);

        // 페이지 정보 세팅
        searchVO.setPageInfo();

        System.out.println(searchVO);
//        List<BoardVO> list = boardService.selectBoardList();
        model.addAttribute("selectList", boardService.selectBoardList(searchVO));
        return "board_list";
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/writeForm")
    public String writeForm(){
        return "board_write_form";
    }

    // 게시글 작성
    @PostMapping("/writeBoard")
    public String writeBoard(BoardVO boardVO, HttpSession session){
        // 세션에 저장된 로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        // boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.insertBoard(boardVO);
        return "redirect:/board/goBoardList";
    }
    // 목록조회, 글쓰기, 상세조회, 수정, 삭제

    // 상세 내용 조회
    @GetMapping("/content")
    public String goTitle(@RequestParam(name = "boardNum") int boardNum, Model model){
//        BoardVO list = boardService.selectDetail(boardNum);
        // 조회수 카운팅
        boardService.updateReadCnt(boardNum);

        // 게시글 목록 조회 및 데이터 html에 전달
        model.addAttribute("detail", boardService.selectDetail(boardNum));

        // 댓글 목록 조회 및 조회 데이터 html에 전달
        model.addAttribute("replyList", replyService.selectReplyList(boardNum));

        return "board_detail";
    }

    // 게시글 수정 화면으로 이동
    @GetMapping("/goUpdate")
    public String updateForm(@RequestParam(name = "boardNum") int boardNum, Model model){
        BoardVO update = boardService.selectDetail(boardNum);
        model.addAttribute("boardDetail", update);
        return "update_form";
    }

    // 게시물 수정
    @PostMapping("/editBoard")
    public String updateBoard(BoardVO boardVO){
        boardService.updateBoard(boardVO);
        return "redirect:/board/content?boardNum=" + boardVO.getBoardNum();
    }

    // 게시물 삭제
    @GetMapping("/goDelete")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/board/goBoardList";
    }


}

    





