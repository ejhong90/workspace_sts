package com.green.Board2.controller;

import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.channels.Pipe;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    // 로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }


    // 회원가입 페이지로 이동
    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    // 회원가입 완료 및 정보를 DB에 저장
    @PostMapping("/confirmJoin")
    public String confirmJoin(MemberVO memberVO){
        memberService.insertMember(memberVO);
        return "redirect:/member/loginForm";
    }

    // 로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.selectLogin(memberVO);

        // 로그인 정보 조회가 됐으면
        if(loginInfo != null){
            // 세션에 로그인 정보를 저장
            session.setAttribute("loginInfo", loginInfo);
        }
        return "login_result";
    }

    // 로그아웃 (세션에 저장된 로그인 정보 삭제)
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/goBoardList";
    }



}
