package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "BoardService")
    private BoardServiceImpl boardService;


    // 게시글 목록 페이지로 가기
    @GetMapping("/")
    public String selectBoard(Model model){
        // 게시판 목록 조회
        List<BoardVO> list = boardService.selectBoard();
        // 조회한 목록을 html로 전달 ("넘어갈때의 명칭" , 실제 넘겨지는 자바에서의 객체명)
        model.addAttribute("selectBoard", list);

        // 조회한 데이터의 수를 확인하는 출력문
//        System.out.println(list.size());
        return "board_list";

    }

    // 글쓰기 페이지로 이동
    @GetMapping("/goWrite")
    public String goInsert(){
        return "board_write_form";
    }

    // 글 등록
    @PostMapping("/writeForm")
    public String insertBoard(BoardVO boardVO){
        // 게시물 작성
        boardService.writeForm(boardVO);
        return "redirect:/";
    }

    // 제목 클릭 후 글 내용 상세 조회
    @GetMapping("/boardDetail")
    public String goBoardDetail(@RequestParam(name = "boardNum") int boardNum, Model model){
        BoardVO detail = boardService.selectDetail(boardNum);
        boardService.readCnt(boardNum);
        model.addAttribute("boardDetail",detail);
        return "board_detail";
    }

    // 해당 게시물 삭제
    @GetMapping("/deleteForm")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/";
    }

    // 클릭 시 수정 페이지로 이동
    @GetMapping("/updateForm")
    public String goUpdate(@RequestParam(name = "boardNum") int boardNum, Model model){
        // 수정하고자 하는 게시글 data 조회
        BoardVO update = boardService.selectDetail(boardNum);
        // html 전달
        model.addAttribute("boardDetail", update);
        return "update_form";
    }
    // 해당 게시물 수정
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO boardVO){
        // 게시글 수정
        boardService.updateBoard(boardVO);
        // 수정한 게시글 data를 상세화면 controller로 전달

        return "redirect:/boardDetail?boardNum=" + boardVO.getBoardNum();
    }



}
